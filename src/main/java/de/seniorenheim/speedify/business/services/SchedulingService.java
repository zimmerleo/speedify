package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.FinanceValues;
import de.seniorenheim.speedify.data.dtos.finance.TransactionCreationDto;
import de.seniorenheim.speedify.data.entities.finance.Transaction;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SchedulingService {

    private final TransactionService  transactionService;
    private final UserService userService;
    private final MembershipService  membershipService;
    private final ForwardingAgencyService forwardingAgencyService;
    private final Random random = new  Random();
    private final TruckService truckService;


    @Scheduled(cron = "0 0 0 L * *")
    public void monthly() {
        List<Transaction> allTransactions = getTransactionsInLastXMonths(1);
        doGasAndTolls(new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(1L) || t.getPurpose().getId().equals(2L)).toList());
        doPayChecks(new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(3L)).toList());
    }

    @Scheduled(cron = "0 0 0 10 3,6,9,12 *")
    public void est_kst() {
        Map<String, BigDecimal> revenueTransactions = new ArrayList<>(getTransactionsInLastXMonths(3)).stream()
                .filter(t -> t.getPurpose().getId().equals(3L))
                .collect(Collectors.groupingBy(t -> t.getPayee().getIban(), Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));

        for (Map.Entry<String, BigDecimal> mapEntry : revenueTransactions.entrySet()) {
            ForwardingAgency forwardingAgency = forwardingAgencyService.getByIban(mapEntry.getKey());

            Long legalForm = forwardingAgency.getLegalForm().getId();
            BigDecimal taxes = BigDecimal.ZERO;
            if (legalForm.equals(1L) || legalForm.equals(2L) || legalForm.equals(3L) || legalForm.equals(4L)) {
                BigDecimal gstCompensation = mapEntry.getValue().multiply(FinanceValues.gewerbesteuersatz).multiply(FinanceValues.gewerbesteuerEinkommensteuerHebesatz);
                if (mapEntry.getValue().compareTo(FinanceValues.einkommensteuergrenzen[0]) <= 0) return;
                if (mapEntry.getValue().compareTo(FinanceValues.einkommensteuergrenzen[1]) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(FinanceValues.einkommensteuergrenzen[0]);
                    taxes = taxes.add(taxEligible.multiply(FinanceValues.einkommensteuersätze[0]));
                }
                if (mapEntry.getValue().compareTo(FinanceValues.einkommensteuergrenzen[2]) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(FinanceValues.einkommensteuergrenzen[1]);
                    taxes = taxes.add(taxEligible.multiply(FinanceValues.einkommensteuersätze[1]));
                }
                if (mapEntry.getValue().compareTo(FinanceValues.einkommensteuergrenzen[3]) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(FinanceValues.einkommensteuergrenzen[2]);
                    taxes = taxes.add(taxEligible.multiply(FinanceValues.einkommensteuersätze[2]));
                }
                if (mapEntry.getValue().compareTo(FinanceValues.einkommensteuergrenzen[3]) > 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(FinanceValues.einkommensteuergrenzen[3]);
                    taxes = taxes.add(taxEligible.multiply(FinanceValues.einkommensteuersätze[3]));
                }

                transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(taxes.subtract(gstCompensation))
                        .purpose(13L)
                        .job(null)
                        .build());
                log.debug("Einkommenssteuer gezahlt.");
            } else {
                transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(mapEntry.getValue().multiply(FinanceValues.koerperschaftssteuersatz))
                        .purpose(16L)
                        .job(null)
                        .build());
                log.debug("Körperschaftssteuer gezahlt.");
            }
        }
    }

    @Scheduled(cron = "0 0 0 15 2,5,8,11 *")
    public void gst() {
        Map<String, BigDecimal> revenueTransactions = new ArrayList<>(getTransactionsInLastXMonths(3)).stream()
                .filter(t -> t.getPurpose().getId().equals(3L))
                .collect(Collectors.groupingBy(t -> t.getPayee().getIban(), Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));

        for (Map.Entry<String, BigDecimal> mapEntry : revenueTransactions.entrySet()) {
            ForwardingAgency forwardingAgency = forwardingAgencyService.getByIban(mapEntry.getKey());
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(mapEntry.getValue().multiply(FinanceValues.gewerbesteuersatz).multiply(BigDecimal.valueOf(random.nextInt(200, 400)/100)))
                    .purpose(14L)
                    .job(null)
                    .build());
            log.debug("Gewerbesteuer gezahlt.");
        }
    }

    @Scheduled(cron = "0 0 0 1 1 *")
    public void yearly() {
        for (ForwardingAgency forwardingAgency : forwardingAgencyService.getAll()) {
            List<Membership> currentMemberships = membershipService.getAll().stream().filter(m -> m.getForwardingAgency().getId().equals(forwardingAgency.getId()) && m.getUntil().equals(LocalDate.of(9999, 12, 31))).toList();
            if (currentMemberships.isEmpty()) {
                forwardingAgencyService.delete(forwardingAgency.getId());
                log.debug("ForwardingAgency {} wurde aufgrund keiner Mitgliedschaften gelöscht.", forwardingAgency.getId());
                continue;
            }
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(FinanceValues.berufskraftfahrerqualifikation.multiply(BigDecimal.valueOf(currentMemberships.size())))
                    .purpose(18L)
                    .job(null)
                    .build());
            int truckCount = 0;
            for (Membership membership : currentMemberships) {
                truckCount += truckService.getAllByUserId(membership.getUser().getId()).size();
            }
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(FinanceValues.kfzSteuer.add(FinanceValues.kfzHaftpflicht.add(FinanceValues.kfzVollkasko.add(FinanceValues.kfzHauptuntersuchung))).multiply(BigDecimal.valueOf(truckCount)))
                    .purpose(17L)
                    .job(null)
                    .build());
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(FinanceValues.euGemeinschaftslizenz.add(FinanceValues.euGemeinschaftslizenzKopie.multiply(BigDecimal.valueOf(truckCount))))
                    .purpose(18L)
                    .job(null)
                    .build());
        }
        log.debug("Berufskraftfahrerqualifikation und EU-Gemeinschaftslizenz gezahlt.");
        log.debug("KFZ-Steuer, KFZ-Versicherung und Hauptuntersuchung gezahlt.");
    }

    private void doGasAndTolls(List<Transaction> gasAndTollTransactions) {
        Map<String, Map<String, BigDecimal>> mapmap = new HashMap<>();
        for (Transaction transaction : gasAndTollTransactions) {
            User user = userService.getByIban(transaction.getPayer().getIban());
            Membership membership = membershipService.getByUserAndTimestampBetweenSinceAndUntil(user.getId(), transaction.getProcessedAt().toLocalDate());

            if (mapmap.containsKey(membership.getForwardingAgency().getBankAccount().getIban())) {
                Map<String, BigDecimal> map = mapmap.get(membership.getForwardingAgency().getBankAccount().getIban());
                if (map.containsKey(user.getBankAccount().getIban())) {
                    map.put(user.getBankAccount().getIban(), map.get(user.getBankAccount().getIban()).add(transaction.getAmount()));
                } else {
                    map.put(user.getBankAccount().getIban(), transaction.getAmount());
                }
            } else {
                Map<String, BigDecimal> map = new HashMap<>();
                map.put(user.getBankAccount().getIban(), transaction.getAmount());
                mapmap.put(membership.getForwardingAgency().getBankAccount().getIban(), map);
            }
        }

        for (Map.Entry<String, Map<String, BigDecimal>> mapmapEntry : mapmap.entrySet()) {
            for (Map.Entry<String, BigDecimal> mapEntry : mapmapEntry.getValue().entrySet()) {
                transactionService.save(mapmapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(mapEntry.getKey())
                        .amount(mapEntry.getValue())
                        .purpose(19L)
                        .job(null)
                        .build());
            }
        }
        log.debug("Tank und Maut rückerstattet.");
    }

    private void doPayChecks(List<Transaction> revenueTransactions) {
        Map<String, Map<String, BigDecimal>> mapmap = new HashMap<>();
        for (Transaction transaction : revenueTransactions) {
            User user = transaction.getJob().getTruck().getOwner();
            ForwardingAgency forwardingAgency = forwardingAgencyService.getByIban(transaction.getPayee().getIban());

            if (mapmap.containsKey(forwardingAgency.getBankAccount().getIban())) {
                Map<String, BigDecimal> map = mapmap.get(forwardingAgency.getBankAccount().getIban());
                if (map.containsKey(user.getBankAccount().getIban())) {
                    map.put(user.getBankAccount().getIban(), map.get(user.getBankAccount().getIban()).add(transaction.getAmount()));
                } else {
                    map.put(user.getBankAccount().getIban(), transaction.getAmount());
                }
            } else {
                Map<String, BigDecimal> map = new HashMap<>();
                map.put(user.getBankAccount().getIban(), transaction.getAmount());
                mapmap.put(forwardingAgency.getBankAccount().getIban(), map);
            }
        }

        for (Map.Entry<String, Map<String, BigDecimal>> mapmapEntry : mapmap.entrySet()) {
            for (Map.Entry<String, BigDecimal> mapEntry : mapmapEntry.getValue().entrySet()) {
                BigDecimal paycheckVolume = FinanceValues.fixgehaltArbeitnehmer.add(mapEntry.getValue().multiply(FinanceValues.umsatzanteilArbeitnehmer));
                boolean lst = paycheckVolume.compareTo(FinanceValues.lohnsteuerMindestgehalt) >= 0;
                boolean uv = paycheckVolume.multiply(FinanceValues.uvBeitragssatz).compareTo(FinanceValues.uvMindestbeitrag) > 0;

                transactionService.save(mapmapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(mapEntry.getKey())
                        .amount(lst ? paycheckVolume.multiply(BigDecimal.ONE.subtract(FinanceValues.lohnsteuersatz.add(FinanceValues.sozialversicherungsbeitragssatz)))
                                : paycheckVolume.multiply(BigDecimal.ONE.subtract(FinanceValues.sozialversicherungsbeitragssatz)))
                        .purpose(10L)
                        .job(null)
                        .build());
                transactionService.save(mapmapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(paycheckVolume.multiply(uv ? FinanceValues.sozialversicherungsbeitragssatz.add(FinanceValues.uvBeitragssatz)
                                : FinanceValues.sozialversicherungsbeitragssatz).add(uv ? BigDecimal.ZERO : FinanceValues.uvMindestbeitrag))
                        .purpose(11L)
                        .job(null)
                        .build());
                transactionService.save(mapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(paycheckVolume.multiply(FinanceValues.sozialversicherungsbeitragssatz))
                        .purpose(11L)
                        .job(null)
                        .build());
                if (lst) {
                    transactionService.save(mapEntry.getKey(), TransactionCreationDto.builder()
                            .payeeIban(null)
                            .amount(paycheckVolume.multiply(FinanceValues.lohnsteuersatz))
                            .purpose(12L)
                            .job(null)
                            .build());
                }
            }
        }
        log.debug("Gehälter gezahlt.");
    }

    private List<Transaction> getTransactionsInLastXMonths(long minusMonths) {
        return transactionService.getAllByProcessedAtAfter(LocalDateTime.now().minusMonths(minusMonths));
    }
}

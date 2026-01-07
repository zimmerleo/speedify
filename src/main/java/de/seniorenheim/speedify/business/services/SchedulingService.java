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
        List<Transaction> allTransactions = transactionService.getAllByProcessedAtAfter(LocalDateTime.now().minusMonths(1));
        doGasAndTolls(new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(1L) || t.getPurpose().getId().equals(2L)).toList());
        doPayChecks(new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(3L)).toList());
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
                // RV 18.6% aufgeteilt auf AN und AG
                // KV 14.6% aufgeteilt auf AN und AG
                // AV 2.6% aufgeteilt auf AN und AG
                // PV 4.2% aufgeteilt auf AN und AG
                // UV mindestens 62€, 1.3% vom AG
                // Lohnsteuer 12% vom Bruttolohn

                BigDecimal paycheckVolume = mapEntry.getValue().multiply(BigDecimal.valueOf(0.25)).add(BigDecimal.valueOf(2000));
                boolean lst = paycheckVolume.compareTo(BigDecimal.valueOf(1029000)) >= 0;
                boolean uv = paycheckVolume.multiply(BigDecimal.valueOf(0.013)).compareTo(BigDecimal.valueOf(62)) > 0;

                transactionService.save(mapmapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(mapEntry.getKey())
                        .amount(lst ? paycheckVolume.multiply(BigDecimal.valueOf(0.68)) : paycheckVolume.multiply(BigDecimal.valueOf(0.8)))
                        .purpose(10L)
                        .job(null)
                        .build());
                transactionService.save(mapmapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(paycheckVolume.multiply(BigDecimal.valueOf(uv ? 0.213 : 0.2)).add(uv ? BigDecimal.ZERO : BigDecimal.valueOf(62)))
                        .purpose(11L)
                        .job(null)
                        .build());
                transactionService.save(mapEntry.getKey(), TransactionCreationDto.builder()
                        .payeeIban(null)
                        .amount(paycheckVolume.multiply(BigDecimal.valueOf(0.2)))
                        .purpose(11L)
                        .job(null)
                        .build());
                if (lst) {
                    transactionService.save(mapEntry.getKey(), TransactionCreationDto.builder()
                            .payeeIban(null)
                            .amount(paycheckVolume.multiply(BigDecimal.valueOf(0.12)))
                            .purpose(12L)
                            .job(null)
                            .build());
                }
            }
        }
        log.debug("Gehälter gezahlt.");
    }

    @Scheduled(cron = "0 0 0 10 4,7,10,1 *")
    public void ust() {
        // Umsatzsteuer 19%
        log.info("Umsatzsteuer...");
    }

    @Scheduled(cron = "0 0 0 10 3,6,9,12 *")
    public void est_kst() {
        List<Transaction> allTransactions = transactionService.getAllByProcessedAtAfter(LocalDateTime.now().minusMonths(3));
        Map<String, BigDecimal> revenueTransactions = new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(3L))
                .collect(Collectors.groupingBy(t -> t.getPayee().getIban(), Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));

        for (Map.Entry<String, BigDecimal> mapEntry : revenueTransactions.entrySet()) {
            ForwardingAgency forwardingAgency = forwardingAgencyService.getByIban(mapEntry.getKey());

            Long legalForm = forwardingAgency.getLegalForm().getId();
            BigDecimal taxes = BigDecimal.ZERO;
            BigDecimal gstCompensation = mapEntry.getValue().multiply(BigDecimal.valueOf(0.035)).multiply(BigDecimal.valueOf(3.8));
            if (legalForm.equals(1L) || legalForm.equals(2L) || legalForm.equals(3L) || legalForm.equals(4L)) {
                if (mapEntry.getValue().compareTo(BigDecimal.valueOf(290100)) <= 0) return;
                if (mapEntry.getValue().compareTo(BigDecimal.valueOf(425125)) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(BigDecimal.valueOf(290100));
                    taxes = taxes.add(taxEligible.multiply(BigDecimal.valueOf(0.19)));
                }
                if (mapEntry.getValue().compareTo(BigDecimal.valueOf(1669000)) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(BigDecimal.valueOf(425125));
                    taxes = taxes.add(taxEligible.multiply(BigDecimal.valueOf(0.33)));
                }
                if (mapEntry.getValue().compareTo(BigDecimal.valueOf(6945625)) <= 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(BigDecimal.valueOf(1669000));
                    taxes = taxes.add(taxEligible.multiply(BigDecimal.valueOf(0.42)));
                }
                if (mapEntry.getValue().compareTo(BigDecimal.valueOf(6945625)) > 0) {
                    BigDecimal taxEligible = mapEntry.getValue().subtract(BigDecimal.valueOf(6945625));
                    taxes = taxes.add(taxEligible.multiply(BigDecimal.valueOf(0.45)));
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
                        .amount(mapEntry.getValue().multiply(BigDecimal.valueOf(0.15)))
                        .purpose(16L)
                        .job(null)
                        .build());
                log.debug("Körperschaftssteuer gezahlt.");
            }
        }
    }

    @Scheduled(cron = "0 0 0 15 2,5,8,11 *")
    public void gst() {
        // Gewerbesteuer = 3.5% * Gewerbeertrag * Hebesatz; 3.5% * Gewerbeertrag * 3.8 Anrechung auf ESt aber nicht auf KSt
        List<Transaction> allTransactions = transactionService.getAllByProcessedAtAfter(LocalDateTime.now().minusMonths(3));
        Map<String, BigDecimal> revenueTransactions = new ArrayList<>(allTransactions).stream()
                .filter(t -> t.getPurpose().getId().equals(3L))
                .collect(Collectors.groupingBy(t -> t.getPayee().getIban(), Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));

        for (Map.Entry<String, BigDecimal> mapEntry : revenueTransactions.entrySet()) {
            ForwardingAgency forwardingAgency = forwardingAgencyService.getByIban(mapEntry.getKey());
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(mapEntry.getValue().multiply(BigDecimal.valueOf(0.035)).multiply(BigDecimal.valueOf(random.nextInt(200, 400)/100)))
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
                    .amount(BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(currentMemberships.size())))
                    .purpose(18L)
                    .job(null)
                    .build());
            int truckCount = 0;
            for (Membership membership : currentMemberships) {
                truckCount += truckService.getAllByUserId(membership.getUser().getId()).size();
            }
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(BigDecimal.valueOf(8900).multiply(BigDecimal.valueOf(truckCount)))
                    .purpose(18L)
                    .job(null)
                    .build());
            transactionService.save(forwardingAgency.getBankAccount().getIban(), TransactionCreationDto.builder()
                    .payeeIban(null)
                    .amount(BigDecimal.valueOf(100).add(BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(truckCount))))
                    .purpose(17L)
                    .job(null)
                    .build());
        }
        log.debug("Berufskraftfahrerqualifikation und EU-Gemeinschaftslizenz gezahlt.");
        log.debug("KFZ-Steuer, KFZ-Versicherung und Hauptuntersuchung gezahlt.");
    }
}

package de.seniorenheim.speedify.business.util;

import de.seniorenheim.speedify.data.dtos.dlcs.DLCResponseDto;
import de.seniorenheim.speedify.data.dtos.finance.BankAccountResponseDto;
import de.seniorenheim.speedify.data.dtos.finance.TransactionResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.LegalFormResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.LevelResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles.CareerResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles.RoleResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.relations.RelationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.relations.RelationTypeResponseDto;
import de.seniorenheim.speedify.data.dtos.jobs.JobResponseDto;
import de.seniorenheim.speedify.data.dtos.jobs.PayloadResponseDto;
import de.seniorenheim.speedify.data.dtos.jobs.PayloadTypeResponseDto;
import de.seniorenheim.speedify.data.dtos.locations.CityResponseDto;
import de.seniorenheim.speedify.data.dtos.locations.CompanyResponseDto;
import de.seniorenheim.speedify.data.dtos.locations.CountryResponseDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckResponseDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckTypeResponseDto;
import de.seniorenheim.speedify.data.dtos.users.RankResponseDto;
import de.seniorenheim.speedify.data.dtos.users.SpecializationResponseDto;
import de.seniorenheim.speedify.data.dtos.users.UserResponseDto;
import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.entities.finance.Transaction;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.LegalForm;
import de.seniorenheim.speedify.data.entities.forwardingagencies.Level;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Application;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Career;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.RelationType;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.entities.jobs.Payload;
import de.seniorenheim.speedify.data.entities.jobs.PayloadType;
import de.seniorenheim.speedify.data.entities.locations.City;
import de.seniorenheim.speedify.data.entities.locations.Company;
import de.seniorenheim.speedify.data.entities.locations.Country;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import de.seniorenheim.speedify.data.entities.trucks.TruckType;
import de.seniorenheim.speedify.data.entities.users.Rank;
import de.seniorenheim.speedify.data.entities.users.Specialization;
import de.seniorenheim.speedify.data.entities.users.User;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public BankAccountResponseDto fromEntity(BankAccount bankAccount){
        return BankAccountResponseDto.builder()
                .iban(bankAccount.getIban())
                .balance(bankAccount.getBalance())
                .build();
    }

    public TruckResponseDto fromEntity(Truck truck){
        return TruckResponseDto.builder()
                .id(truck.getId())
                .licensePlate(truck.getLicensePlate())
                .type(fromEntity(truck.getType()))
                .owner(fromEntity(truck.getOwner()))
                .build();
    }

    public JobResponseDto fromEntity(Job job){
        return JobResponseDto.builder()
                .id(job.getId())
                .payload(fromEntity(job.getPayload()))
                .origin(fromEntity(job.getOrigin()))
                .destination(fromEntity(job.getDestination()))
                .accepted(job.getAccepted())
                .truck(fromEntity(job.getTruck()))
                .kilometersDriven(job.getKilometersDriven())
                .hoursDriven(job.getHoursDriven())
                .completed(job.getCompleted())
                .build();
    }
    public CityResponseDto fromEntity(City city){
        return CityResponseDto.builder()
                .id(city.getId())
                .name(city.getName())
                .locatedIn(fromEntity(city.getLocatedIn()))
                .dlc(fromEntity(city.getDlc()))
                .companies(city.getCompanies().stream().map(this::fromEntity).toList())
                .build();
    }

    public CompanyResponseDto fromEntity(Company company){
        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }

    public CountryResponseDto fromEntity(Country country){
        return CountryResponseDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public DLCResponseDto fromEntity(DLC dlc){
        return DLCResponseDto.builder()
                .id(dlc.getId())
                .name(dlc.getName())
                .build();
    }

    public PayloadResponseDto fromEntity(Payload payload){
        return PayloadResponseDto.builder()
                .id(payload.getId())
                .name(payload.getName())
                .payloadType(fromEntity(payload.getPayloadType()))
                .build();
    }

    public TruckTypeResponseDto fromEntity(TruckType truckType){
        return TruckTypeResponseDto.builder()
                .id(truckType.getId())
                .name(truckType.getName())
                .build();
    }

    public UserResponseDto fromEntity(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .bankAccount(fromEntity(user.getBankAccount()))
                .administrator(user.getAdministrator())
                .build();
    }

    public RankResponseDto fromEntity(Rank rank){
        return RankResponseDto.builder()
                .id(rank.getId())
                .name(rank.getName())
                .superior(rank.getSuperior() != null ? fromEntity(rank.getSuperior()) : null)
                .build();
    }

    public SpecializationResponseDto fromEntity(Specialization specialization){
        return SpecializationResponseDto.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .build();
    }

    public PayloadTypeResponseDto fromEntity(PayloadType payloadType){
        return PayloadTypeResponseDto.builder()
                .id(payloadType.getId())
                .name(payloadType.getName())
                .specialization(fromEntity(payloadType.getSpecialization()))
                .build();
    }

    public TransactionResponseDto fromEntity(Transaction transaction){
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .payer(fromEntity(transaction.getPayer()))
                .payee(fromEntity(transaction.getPayee()))
                .amount(transaction.getAmount())
                .purpose(transaction.getPurpose())
                .processedAt(transaction.getProcessedAt())
                .build();
    }

    public MembershipResponseDto fromEntity(Membership membership){
        return MembershipResponseDto.builder()
                .id(membership.getId())
                .forwardingAgency(fromEntity(membership.getForwardingAgency()))
                .user(fromEntity(membership.getUser()))
                .since(membership.getSince())
                .until(membership.getUntil())
                .role(fromEntity(membership.getRole()))
                .build();
    }

    public ApplicationResponseDto fromEntity(Application application){
        return ApplicationResponseDto.builder()
                .id(application.getId())
                .forwardingAgency(fromEntity(application.getForwardingAgency()))
                .user(fromEntity(application.getUser()))
                .role(fromEntity(application.getRole()))
                .text(application.getText())
                .build();
    }

    public RelationResponseDto fromEntity(Relation relation){
        return RelationResponseDto.builder()
                .forwardingAgency_1(fromEntity(relation.getForwardingAgency_1()))
                .forwardingAgency_2(fromEntity(relation.getForwardingAgency_2()))
                .relationType(fromEntity(relation.getRelationType()))
                .build();
    }

    public RelationTypeResponseDto fromEntity(RelationType relationType){
        return RelationTypeResponseDto.builder()
                .id(relationType.getId())
                .name(relationType.getName())
                .build();
    }

    public LevelResponseDto fromEntity(Level level){
        return LevelResponseDto.builder()
                .id(level.getId())
                .name(level.getName())
                .build();
    }

    public LegalFormResponseDto fromEntity(LegalForm legalForm){
        return LegalFormResponseDto.builder()
                .id(legalForm.getId())
                .name(legalForm.getName())
                .capitalStock(legalForm.getCapitalStock())
                .build();
    }

    public ForwardingAgencyResponseDto fromEntity(ForwardingAgency forwardingAgency){
        return ForwardingAgencyResponseDto.builder()
                .id(forwardingAgency.getId())
                .name(forwardingAgency.getName())
                .description(forwardingAgency.getDescription())
                .level(fromEntity(forwardingAgency.getLevel()))
                .xp(forwardingAgency.getXp())
                .legalForm(fromEntity(forwardingAgency.getLegalForm()))
                .bankAccounts(forwardingAgency.getBankAccounts().stream().map(this::fromEntity).toList())
                .build();
    }

    public RoleResponseDto fromEntity(Role role){
        return RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .superior(role.getSuperior() != null ? fromEntity(role.getSuperior()) : null)
                .career(fromEntity(role.getCareer()))
                .build();
    }

    public CareerResponseDto fromEntity(Career career){
        return CareerResponseDto.builder()
                .id(career.getId())
                .name(career.getName())
                .description(career.getDescription())
                .build();
    }
}

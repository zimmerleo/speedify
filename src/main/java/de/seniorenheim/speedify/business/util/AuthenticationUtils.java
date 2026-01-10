package de.seniorenheim.speedify.business.util;

import de.seniorenheim.speedify.data.entities.users.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authUtils")
public class AuthenticationUtils {

    public static LoginUser getCurrentUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    public static boolean hasAnyAgencyRole() {
        return getAuthentication().getAuthorities().stream().anyMatch(authority ->
                authority.getAuthority().startsWith("AGENCY_")
        );
    }

    public static boolean isInAgency(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(authority ->
                authority.getAuthority().startsWith("AGENCY_" + forwardingAgencyId + "_")
        );
    }

    public static boolean isJuniorBerufskraftfahrer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_10"));
    }

    public static boolean isBerufskraftfahrer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_11"));
    }

    public static boolean isSeniorBerufskraftfahrer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_12"));
    }

    public static boolean isLeitenderBerufskraftfahrer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_13"));
    }

    public static boolean isJuniorBuchhalter(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_20"));
    }

    public static boolean isBuchhalter(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_21"));
    }

    public static boolean isSeniorBuchhalter(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_22"));
    }

    public static boolean isLeitenderBuchhalter(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_23"));
    }

    public static boolean isJuniorAnalyst(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_24"));
    }

    public static boolean isAnalyst(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_25"));
    }

    public static boolean isSeniorAnalyst(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_26"));
    }

    public static boolean isLeitenderAnalyst(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_27"));
    }

    public static boolean isOperationsManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_30"));
    }

    public static boolean isSeniorOperationsManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_31"));
    }

    public static boolean isFinanceManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_32"));
    }

    public static boolean isSeniorFinanceManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_33"));
    }

    public static boolean isHRManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_34"));
    }

    public static boolean isSeniorHRManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_35"));
    }

    public static boolean isQAManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_36"));
    }

    public static boolean isSeniorQAManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_37"));
    }

    public static boolean isMarketingManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_38"));
    }

    public static boolean isSeniorMarketingManager(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_39"));
    }

    public static boolean isChiefOperatingOfficer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_95"));
    }

    public static boolean isChiefFinancialOfficer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_96"));
    }

    public static boolean isChiefRecruitingOfficer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_97"));
    }

    public static boolean isChiefMarketingOfficer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_98"));
    }

    public static boolean isChiefExecutiveOfficer(Long forwardingAgencyId) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("AGENCY_" + forwardingAgencyId + "_99"));
    }

    public static boolean isCLevel(Long forwardingAgencyId) {
        return isChiefExecutiveOfficer(forwardingAgencyId) ||
                isChiefOperatingOfficer(forwardingAgencyId) ||
                isChiefFinancialOfficer(forwardingAgencyId) ||
                isChiefRecruitingOfficer(forwardingAgencyId) ||
                isChiefMarketingOfficer(forwardingAgencyId);
    }

    public static boolean isManager(Long forwardingAgencyId) {
        return isOperationsManager(forwardingAgencyId) ||
                isSeniorOperationsManager(forwardingAgencyId) ||
                isFinanceManager(forwardingAgencyId) ||
                isSeniorFinanceManager(forwardingAgencyId) ||
                isHRManager(forwardingAgencyId) ||
                isSeniorHRManager(forwardingAgencyId) ||
                isQAManager(forwardingAgencyId) ||
                isSeniorQAManager(forwardingAgencyId) ||
                isMarketingManager(forwardingAgencyId) ||
                isSeniorMarketingManager(forwardingAgencyId);
    }

    public static boolean isOperations(Long forwardingAgencyId) {
        return isJuniorBerufskraftfahrer(forwardingAgencyId) ||
                isBerufskraftfahrer(forwardingAgencyId) ||
                isSeniorBerufskraftfahrer(forwardingAgencyId) ||
                isLeitenderBerufskraftfahrer(forwardingAgencyId) ||
                isOperationsManager(forwardingAgencyId) ||
                isSeniorOperationsManager(forwardingAgencyId) ||
                isChiefOperatingOfficer(forwardingAgencyId);
    }

    public static boolean isFinance(Long forwardingAgencyId) {
        return isJuniorBuchhalter(forwardingAgencyId) ||
                isBuchhalter(forwardingAgencyId) ||
                isSeniorBuchhalter(forwardingAgencyId) ||
                isLeitenderBuchhalter(forwardingAgencyId) ||
                isFinanceManager(forwardingAgencyId) ||
                isSeniorFinanceManager(forwardingAgencyId) ||
                isChiefFinancialOfficer(forwardingAgencyId);
    }

    public static boolean isQA(Long forwardingAgencyId) {
        return isJuniorAnalyst(forwardingAgencyId) ||
                isAnalyst(forwardingAgencyId) ||
                isSeniorAnalyst(forwardingAgencyId) ||
                isLeitenderAnalyst(forwardingAgencyId) ||
                isQAManager(forwardingAgencyId) ||
                isSeniorQAManager(forwardingAgencyId) ||
                isChiefOperatingOfficer(forwardingAgencyId);
    }

    public static boolean isHR(Long forwardingAgencyId) {
        return isHRManager(forwardingAgencyId) ||
                isSeniorHRManager(forwardingAgencyId) ||
                isChiefRecruitingOfficer(forwardingAgencyId);
    }

    public static boolean isMarketing(Long forwardingAgencyId) {
        return isMarketingManager(forwardingAgencyId) ||
                isSeniorMarketingManager(forwardingAgencyId) ||
                isChiefMarketingOfficer(forwardingAgencyId);
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}

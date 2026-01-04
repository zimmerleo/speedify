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
        return getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().startsWith("AGENCY_"));
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}

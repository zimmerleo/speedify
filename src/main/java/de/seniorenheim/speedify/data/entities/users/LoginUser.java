package de.seniorenheim.speedify.data.entities.users;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class LoginUser implements UserDetails {

    private final User user;
    private final Role role;
    private final ForwardingAgency forwardingAgency;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (Boolean.TRUE.equals(user.getAdministrator())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (role != null &&  forwardingAgency != null) {
            authorities.add(new SimpleGrantedAuthority("AGENCY_" + forwardingAgency.getId() + "_" + role.getId()));
        }
        return authorities;
    }

    public Role getRole() {
        return role;
    }

    public ForwardingAgency getForwardingAgency() {
        return forwardingAgency;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
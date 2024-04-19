package com.dutybot2.dutybot2.details;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class CadetDetails implements UserDetails {
    private CadetUser cadetUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(cadetUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return cadetUser.getPassword();
    }

    @Override
    public String getUsername() {
        return cadetUser.getUsername();
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
}

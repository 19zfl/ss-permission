package com.ss.service.impl;

import com.ss.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName:LoginUser
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 13:18
 */

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    private List<SimpleGrantedAuthority> authoritiesList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authoritiesList != null) return authoritiesList;
        authoritiesList = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authoritiesList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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

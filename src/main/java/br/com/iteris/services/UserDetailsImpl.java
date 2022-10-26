package br.com.iteris.services;

import br.com.iteris.domain.entities.User;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class UserDetailsImpl extends User implements UserDetails  {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String userName;

    private String password;

    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
    }

    public UserDetailsImpl(String username, String password, Long id, String userName, String password1, List<GrantedAuthority> authorities) {
        super(id);
        this.id = id;
        this.userName = userName;
        this.password = password1;
        this.authorities = authorities;
    }

    @Override
    public Long getId() {
        return id;
    }

    public UserDetailsImpl(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

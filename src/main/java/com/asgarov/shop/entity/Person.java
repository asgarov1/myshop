package com.asgarov.shop.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class Person extends AbstractPersistable<Long> implements UserDetails {

    abstract void setRole();

    { setRole(); }

    @Enumerated(EnumType.STRING)
    Role role;

    @Column
    @NotNull
    @NotEmpty(message = "{error.empty}")
    String firstName;

    @Column
    @NotNull
    @NotEmpty(message = "{error.empty}")
    String lastName;

    @Column
    @NotNull
    @NotEmpty(message = "{error.empty}")
    @Email
    String email;

    @Column
    @NotNull
    @NotEmpty(message = "{error.empty}")
    String password;

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override public boolean isEnabled() {
        return true;
    }

    public enum Role {
        USER, ADMIN;
    }
}

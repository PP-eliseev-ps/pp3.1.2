package com.example.spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractModel implements UserDetails {

    @NotBlank(message = "Name must be required field")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname must be required field")
    @Column(name = "surname")
    private String surname;

    @PositiveOrZero(message = "Age must be greater than -1")
    @Column(name = "age", nullable = false)
    private int age;

    @NotBlank
    @Size(min = 2, message = "Не меньше 5 знаков")
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(min = 2, message = "Не меньше 5 знаков")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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

//    public boolean isAdmin() {
//        if (this.roles != null) {
//            for (Role role : this.roles) {
//                if (role.getId() == 2L) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}

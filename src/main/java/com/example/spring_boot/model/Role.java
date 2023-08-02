package com.example.spring_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractModel implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id, String name) {
        super.setId(id);
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Role) {
//            return name.equals(((Role) obj).getAuthority());
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return name.hashCode();
//    }

    @Override
    public String toString() {
        return name;
    }
}

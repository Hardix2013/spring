package ru.mylife54.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role{// implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    List<User> users;

//    @Override
    public String getAuthority() {
        return null;
    }
}

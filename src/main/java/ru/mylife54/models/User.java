package ru.mylife54.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotBlank(message = "Имя должно быть заполнено")
    private String firstname;
    @NotBlank(message = "Фамилия должна быть заполнена")
    private String lastname;
    @NotBlank(message = "Никнейм должен быть заполнен")
    @Column(unique = true)
    private String nickname;
    @NotBlank(message = "Логин должен быть заполнен")
    private String login;
    @Column(unique = true)
    @NotBlank(message = "Пароль должен быть заполнен")
    private String password;
    @Transient
    private String passwordConfirm;
    @NotBlank(message = "Емэйл должен быть заполнен")
    @Column(unique = true)
    private String email;
    @Transient
    @NotBlank(message = "Емэйл должен быть заполнен")
    private String emailConfirm;
    private String profile;
    private Date birthday;
    private Date lastVisit;
    private long balance;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Role.class)
    private List<Role> roles;

}

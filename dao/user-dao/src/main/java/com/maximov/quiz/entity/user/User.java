package com.maximov.quiz.entity.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * Сущность Пользователь
 */
@Entity
@Table(name = "USER", indexes = {@Index(name = "IDX_LOGIN", columnList = "login"),
        @Index(name = "IDX_ROLE", columnList = "role")})
public class User {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Логин
     */
    @Column(name = "LOGIN", unique = true, length = 10)
    private String login;

    /**
     * Пароль
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * РОЛЬ
     */
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * Для отпимистичной блокировки
     */
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

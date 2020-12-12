package com.ProftaakS34.Opinion.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String encryptedPassword;

    @Column(name = "salt")
    private String salt;

    public UserDAO(String username, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }
    public UserDAO(String username, String encryptedPassword, String salt) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.salt = salt;
    }
}

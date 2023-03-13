package com.investree.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "idPeminjam")
    @JsonIgnore
    private List<com.investree.demo.model.Transaksi> peminjam;

    @OneToMany(mappedBy = "idMeminjam")
    @JsonIgnore
    private List<Transaksi> meminjam;
}


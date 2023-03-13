package com.investree.demo.model.oauth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name = "role_path")
public class RolePath implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    private String pattern;

    private String method;

    @ManyToOne(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Role role;
}
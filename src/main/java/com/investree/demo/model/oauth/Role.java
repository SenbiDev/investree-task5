package com.investree.demo.model.oauth;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.investree.demo.model.Users;
import javax.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

@Entity
@Data
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "role_name_and_type",
                        columnNames = {"type", "name"}
                )
        }
)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    private String type;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePath> rolePaths;

    @JsonIgnore
    @ManyToMany(targetEntity = Users.class, mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Users> users;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }
}

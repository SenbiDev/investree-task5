package com.investree.demo.repository;

import com.investree.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("FROM Users u WHERE LOWER(u.username) = LOWER(?1)")
    Users findOneByUsername(String username);
}

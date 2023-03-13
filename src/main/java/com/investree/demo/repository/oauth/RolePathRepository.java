package com.investree.demo.repository.oauth;

import com.investree.demo.model.oauth.RolePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePathRepository extends JpaRepository<RolePath, Long> {
    RolePath findOneByName(String rolePathName);
}

package com.investree.demo.repository.otp;

import com.investree.demo.model.otp.OneTimePassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimePasswordRepository extends CrudRepository<OneTimePassword, Long> {
}

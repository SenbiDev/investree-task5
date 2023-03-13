package com.investree.demo.view.impl;

import com.investree.demo.model.otp.OneTimePassword;
import com.investree.demo.repository.otp.OneTimePasswordRepository;
import com.investree.demo.utils.OneTimePasswordHelpService;
import com.investree.demo.view.OneTimePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OneTimePasswordServiceImple implements OneTimePasswordService {
    private final Long expiryInterval = 5L * 60 * 1000;

    @Autowired
    OneTimePasswordRepository oneTimePasswordRepository;

    @Override
    public Map<String, Object> returnOneTimePassword() {
        Map<String, Object> map = new HashMap<>();
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(OneTimePasswordHelpService.createRandomOneTimePassword().get());
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));

        try {
            oneTimePasswordRepository.save(oneTimePassword);
            map.put("data", oneTimePassword);
            map.put("code", 200);
            map.put("status", "sukses");
        } catch(Exception e) {
            map.put("code", 500);
            map.put("status", "gagal");
        }

        return map;
    }
}

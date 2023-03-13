package com.investree.demo.controller;

import com.investree.demo.view.OneTimePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/otp")
public class OneTimePasswordController {
    @Autowired
    OneTimePasswordService oneTimePAsswordService;

    @GetMapping("")
    private ResponseEntity<Map<String, Object>> getOneTimePassword() {
        Map<String, Object> create = oneTimePAsswordService.returnOneTimePassword();
        return new ResponseEntity<>(create, HttpStatus.OK);
    }
}

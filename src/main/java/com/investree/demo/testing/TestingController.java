package com.investree.demo.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingController {
    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    @Before
    public void before() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        String bodyTesting =
                "username=admin@mail.com&" +
                        "password=password&" +
                        "grant_type=password&" +
                        "client_id=my-client-apps&" +
                        "client_secret=password"
                ;
        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);

        ResponseEntity<String> exchange = restTemplate.exchange
                ("http://localhost:8081/api/oauth/token",
                        HttpMethod.POST,
                        entity,
                        String.class
                );
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("oauth2 response =" + exchange.getBody());

        String[] responseSplit = exchange.getBody().split("/\"[a-z]*|[a-z]*_[a-z]*\":");
        String accessToken = Arrays.stream(responseSplit).collect(Collectors.toList()).get(1)
                .replace("\"","")
                .replace(",","");
        System.out.println("access token =" + accessToken);
        this.accessToken = accessToken;
    }

    @Test
    public void saveTransaksi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + this.accessToken);
        String bodyTesting = "{\n" +
                "    \"tenor\": 12,\n" +
                "    \"totalPinjaman\": 12000000,\n" +
                "    \"bungaPersen\": 0.0,\n" +
                "    \"status\":\"belum lunas\"\n" +
                "    \n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        ResponseEntity<String> exchange = restTemplate.exchange
                ("http://localhost:8081/api/v1/transaksi/1/with/2",
                        HttpMethod.POST,
                        entity,
                        String.class
                );
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("save transaksi response =" + exchange.getBody());
    }

    @Test
    public void updateTransaksi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + this.accessToken);
        String bodyTesting = "{\n" +
                "    \"id\": 1,\n" +
                "    \"status\":\"lunas\"\n" +
                "    \n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);
        ResponseEntity<String> exchange = restTemplate.exchange
                ("http://localhost:8081/api/v1/transaksi",
                        HttpMethod.PUT,
                        entity,
                        String.class
                );
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("update transaksi response =" + exchange.getBody());
    }

    @Test
    public void listTransaksi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + this.accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> exchange = restTemplate.exchange
                (
                        "http://localhost:8081/api/v1/transaksi/list?page=0&size=1",
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("list transaksi response =" + exchange.getBody());
    }

    @Test
    public void getOTP() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> exchange = restTemplate.exchange
                ("http://localhost:8081/api/v1/otp",
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("get otp response =" + exchange.getBody());
    }
}

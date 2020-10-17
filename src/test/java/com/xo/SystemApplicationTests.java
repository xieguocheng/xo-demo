package com.xo;

import com.xo.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplication.class)
class SystemApplicationTests {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() {
    }


    @Test
    public void test() {

        String userNameFromToken = jwtTokenUtil.getUserNameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImNyZWF0ZWQiOjE1ODkwMjMzNDEwNzAsImV4cCI6MTU4OTAyMzM1Mn0.voVBLn2DP8ZJ5Tn7_C2kQPDYzLIfoTt84dVJUbE6fUmxFyt_-ruSR7KkMyF430pcACqxu0M4QrswEStCxSK5xA");

        System.out.println(userNameFromToken);

    }

}

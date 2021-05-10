package cn.edu.nciae.onlinejudge.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 13:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2Tests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder(){
        System.out.println(passwordEncoder.encode("123456"));
    }

}

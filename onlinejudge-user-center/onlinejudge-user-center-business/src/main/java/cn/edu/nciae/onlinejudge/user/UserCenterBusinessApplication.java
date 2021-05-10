package cn.edu.nciae.onlinejudge.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 18:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserCenterBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterBusinessApplication.class, args);
    }
}

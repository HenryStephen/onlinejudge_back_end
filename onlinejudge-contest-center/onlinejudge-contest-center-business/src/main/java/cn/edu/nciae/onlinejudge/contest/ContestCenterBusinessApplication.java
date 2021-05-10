package cn.edu.nciae.onlinejudge.contest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 0:21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ContestCenterBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContestCenterBusinessApplication.class, args);
    }
}

package cn.edu.nciae.onlinejudge.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 9:39
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ContentCenterBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterBusinessApplication.class, args);
    }
}

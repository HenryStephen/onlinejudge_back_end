package cn.edu.nciae.onlinejudge.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 17:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StatisticCenterBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticCenterBusinessApplication.class, args);
    }
}

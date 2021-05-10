package cn.edu.nciae.onlinejudge.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 16:56
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.nciae.onlinejudge.statistic.mapper")
public class StatisticCenterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticCenterServiceApplication.class, args);
    }
}

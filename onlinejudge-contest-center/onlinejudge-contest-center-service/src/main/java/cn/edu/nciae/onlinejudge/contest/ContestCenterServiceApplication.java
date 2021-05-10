package cn.edu.nciae.onlinejudge.contest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 0:25
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.nciae.onlinejudge.contest.mapper")
public class ContestCenterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContestCenterServiceApplication.class, args);
    }
}

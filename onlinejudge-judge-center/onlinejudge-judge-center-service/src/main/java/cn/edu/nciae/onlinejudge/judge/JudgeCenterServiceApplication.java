package cn.edu.nciae.onlinejudge.judge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/23 16:10
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.nciae.onlinejudge.judge.mapper")
public class JudgeCenterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeCenterServiceApplication.class, args);
    }
}

package cn.edu.nciae.onlinejudge.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 15:50
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.nciae.onlinejudge.user.mapper")
public class UserCenterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterServiceApplication.class, args);
    }
}

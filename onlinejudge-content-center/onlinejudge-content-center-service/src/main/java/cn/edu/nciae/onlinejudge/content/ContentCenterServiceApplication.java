package cn.edu.nciae.onlinejudge.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 9:39
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.nciae.onlinejudge.content.mapper")
public class ContentCenterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentCenterServiceApplication.class, args);
    }
}

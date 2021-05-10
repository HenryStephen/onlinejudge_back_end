package cn.edu.nciae.onlinejudge.content;

import cn.edu.nciae.onlinejudge.content.message.source.CheckpointSource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.Bindings;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 9:39
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({CheckpointSource.class})
public class ContentCenterBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterBusinessApplication.class, args);
    }
}

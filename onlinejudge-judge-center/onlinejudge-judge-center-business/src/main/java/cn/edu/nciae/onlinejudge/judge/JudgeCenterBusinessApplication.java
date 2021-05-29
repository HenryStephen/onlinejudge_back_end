package cn.edu.nciae.onlinejudge.judge;

import cn.edu.nciae.onlinejudge.judge.message.sink.SubmissionSink;
import cn.edu.nciae.onlinejudge.judge.message.source.SubmissionSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/23 15:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({SubmissionSink.class, SubmissionSource.class})
public class JudgeCenterBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeCenterBusinessApplication.class, args);
    }
}

package cn.edu.nciae.onlinejudge.judge.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 21:48
 */
public interface SubmissionSink {

    @Input("create-submission-input")
    SubscribableChannel createSubmissionInput();
}

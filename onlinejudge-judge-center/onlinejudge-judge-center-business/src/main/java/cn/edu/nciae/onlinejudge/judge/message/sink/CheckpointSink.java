package cn.edu.nciae.onlinejudge.judge.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 20:55
 */
public interface CheckpointSink {

    @Input("add-checkpoint-input")
    SubscribableChannel addCheckpointInput();
}

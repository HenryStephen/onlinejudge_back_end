package cn.edu.nciae.onlinejudge.content.message.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 17:17
 */
public interface CheckpointSource {

    @Output("add-checkpoint-output")
    MessageChannel addCheckpointOutput();
}

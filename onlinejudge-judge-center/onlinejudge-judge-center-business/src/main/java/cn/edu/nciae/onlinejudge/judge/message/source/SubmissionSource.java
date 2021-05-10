package cn.edu.nciae.onlinejudge.judge.message.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 21:44
 */
public interface SubmissionSource {

    @Output("create-submission-output")
    MessageChannel createSubmissionOutput();
}

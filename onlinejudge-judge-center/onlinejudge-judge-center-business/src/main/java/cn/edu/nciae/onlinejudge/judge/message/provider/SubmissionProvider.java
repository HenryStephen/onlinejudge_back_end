package cn.edu.nciae.onlinejudge.judge.message.provider;

import cn.edu.nciae.onlinejudge.judge.message.source.SubmissionSource;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 21:51
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class SubmissionProvider {

    @Resource
    private SubmissionSource submissionSource;

    /**
     * 创建submission
     * @param submissionVO
     * @return
     */
    public void createSubmission(SubmissionVO submissionVO){
        submissionSource.createSubmissionOutput().send(MessageBuilder.withPayload(submissionVO).build());
    }
}

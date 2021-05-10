package cn.edu.nciae.onlinejudge.content.message.provider;

import cn.edu.nciae.onlinejudge.content.domain.Checkpoint;
import cn.edu.nciae.onlinejudge.content.message.source.CheckpointSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 17:23
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CheckpointProvider {

    @Resource
    private CheckpointSource checkpointSource;

    /**
     * 添加测试样例列表(异步)
     * @param checkpointList
     * @return
     */
    public boolean addCheckpointList(List<Checkpoint> checkpointList){
        return checkpointSource.addCheckpointOutput().send(MessageBuilder.withPayload(checkpointList).build());
    }
}

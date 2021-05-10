package cn.edu.nciae.onlinejudge.judge.api;

import cn.edu.nciae.onlinejudge.judge.domain.Checkpoint;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 22:23
 */
public interface CheckpointServiceApi {

    /**
     * 添加测试用例
     * @param checkpoint
     * @return
     */
    boolean save(Checkpoint checkpoint);
}

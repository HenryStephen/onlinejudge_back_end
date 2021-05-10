package cn.edu.nciae.onlinejudge.judge.api;

import cn.edu.nciae.onlinejudge.judge.domain.Run;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/30 23:27
 */
public interface RunServiceApi {

    /**
     * 根据id获取运行参数
     * @param languageRunId
     * @return
     */
    Run getRunById(Integer languageRunId);
}

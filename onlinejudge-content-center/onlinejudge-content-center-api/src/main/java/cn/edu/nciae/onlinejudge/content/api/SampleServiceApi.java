package cn.edu.nciae.onlinejudge.content.api;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 14:49
 */
public interface SampleServiceApi {

    /**
     * 根据题目id删除样例输入输出
     * @param problemId
     * @return
     */
    boolean removeByProblemId(Long problemId);
}

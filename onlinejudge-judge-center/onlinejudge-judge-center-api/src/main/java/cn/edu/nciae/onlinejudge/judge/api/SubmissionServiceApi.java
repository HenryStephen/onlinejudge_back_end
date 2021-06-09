package cn.edu.nciae.onlinejudge.judge.api;

import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionKeyword;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 20:47
 */
public interface SubmissionServiceApi {
    /**
     * 添加submission
     * @param submission
     * @return
     */
    boolean save(Submission submission);

    /**
     * 根据id查找submission
     * @param submissionId
     * @return
     */
    Submission getById(String submissionId);

    /**
     * 根据参数查询submissionList
     * @param page
     * @param submissionKeyword
     * @return
     */
    IPage<SubmissionDTO> getSubmissionListPage(Page page, SubmissionKeyword submissionKeyword);

    /**
     * 获取提交信息的个数
     * @return
     */
    Integer getSubmissionCount();

    /**
     * 根据竞赛id和题目id获得题目的统计信息
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    List<Map<String, Long>> getStatistic(Long competitionId, Long problemDisplayId);
}

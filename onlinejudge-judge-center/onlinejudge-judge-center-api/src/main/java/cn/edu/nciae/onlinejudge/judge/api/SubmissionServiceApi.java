package cn.edu.nciae.onlinejudge.judge.api;

import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionDTO;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionKeyword;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
}

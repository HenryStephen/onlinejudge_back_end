package cn.edu.nciae.onlinejudge.judge.mapper;

import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionDTO;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionKeyword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Entity cn.edu.nciae.onlinejudge.judge.domain.Submission
 */
public interface SubmissionMapper extends BaseMapper<Submission> {

    /**
     * 根据submissionid查找submission
     * @param submissionId
     * @return
     */
    Submission selectBySubmissionId(Long submissionId);

    /**
     * 查询状态信息分页列表
     * @param page
     * @param submissionKeyword
     * @return
     */
    IPage<SubmissionDTO> selectSubmissionVOListPage(Page page, SubmissionKeyword submissionKeyword);
}





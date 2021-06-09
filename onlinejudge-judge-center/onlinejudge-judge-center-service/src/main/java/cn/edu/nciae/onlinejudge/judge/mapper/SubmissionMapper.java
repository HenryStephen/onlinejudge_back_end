package cn.edu.nciae.onlinejudge.judge.mapper;

import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionDTO;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionKeyword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @Entity cn.edu.nciae.onlinejudge.judge.domain.Submission
 */
public interface SubmissionMapper extends BaseMapper<Submission> {

    /**
     * 根据submissionid查找submission
     * @param submissionId
     * @return
     */
    Submission selectBySubmissionId(String submissionId);

    /**
     * 查询状态信息分页列表
     * @param page
     * @param submissionKeyword
     * @return
     */
    IPage<SubmissionDTO> selectSubmissionVOListPage(Page page, SubmissionKeyword submissionKeyword);

    /**
     * 根据竞赛id和题目id获得题目的统计信息
     *
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    List<Map<String, Long>> selectStatistic(Long competitionId, Long problemDisplayId);
}





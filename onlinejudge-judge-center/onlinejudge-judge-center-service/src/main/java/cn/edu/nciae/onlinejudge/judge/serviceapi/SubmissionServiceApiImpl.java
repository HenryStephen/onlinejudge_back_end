package cn.edu.nciae.onlinejudge.judge.serviceapi;

import cn.edu.nciae.onlinejudge.judge.api.SubmissionServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionKeyword;
import cn.edu.nciae.onlinejudge.judge.mapper.SubmissionMapper;
import cn.edu.nciae.onlinejudge.judge.service.impl.SubmissionServiceImpl;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 20:48
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class SubmissionServiceApiImpl extends SubmissionServiceImpl implements SubmissionServiceApi {

    @Autowired
    private SubmissionMapper submissionMapper;

    /**
     * 添加submission
     * @param submission
     * @return
     */
    @Override
    public boolean save(Submission submission){
        return super.save(submission);
    }

    /**
     * 根据id查找submission
     *
     * @param submissionId
     * @return
     */
    @Override
    public Submission getById(String submissionId) {
        return submissionMapper.selectBySubmissionId(submissionId);
    }

    /**
     * 根据参数查询submissionList
     *
     * @param page
     * @param submissionKeyword
     * @return
     */
    @Override
    public IPage<SubmissionDTO> getSubmissionListPage(Page page, SubmissionKeyword submissionKeyword) {
        return submissionMapper.selectSubmissionVOListPage(page, submissionKeyword);
    }

    /**
     * 获取提交信息的个数
     *
     * @return
     */
    @Override
    public Integer getSubmissionCount() {
        return super.count();
    }

    /**
     * 根据竞赛id和题目id获得题目的统计信息
     *
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    @Override
    public List<Map<String, Long>> getStatistic(Long competitionId, Long problemDisplayId) {
        return submissionMapper.selectStatistic(competitionId, problemDisplayId);
    }
}

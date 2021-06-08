package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.mapper.ProblemMapper;
import cn.edu.nciae.onlinejudge.content.mapper.SampleMapper;
import cn.edu.nciae.onlinejudge.content.service.impl.ProblemServiceImpl;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:31
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class ProblemServiceApiImpl extends ProblemServiceImpl implements ProblemServiceApi {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SampleMapper sampleMapper;

    /**
     * 查询题目分页列表
     *
     * @param page
     * @return
     */
    @Override
    public IPage<ProblemDTO> getProblemListPage(Page page, ProblemParam problemParam) {
        return problemMapper.selectProblemVOListPage(page, problemParam);
    }

    /**
     * 查询题目分页列表（管理员）
     *
     * @param page
     * @param problemParam
     * @return
     */
    @Override
    public IPage<ProblemDTO> getProblemListPageAdmin(Page page, ProblemParam problemParam) {
        return problemMapper.selectProblemVOListPageAdmin(page, problemParam);
    }

    /**
     * 查询题目
     *
     * @param problemId
     * @return
     */
    @Override
    public ProblemDTO getProblemVOByPid(Long problemId) {
        return problemMapper.selectProblemVOByPid(problemId);
    }

    /**
     * 根据id查找题目
     *
     * @param problemId
     * @return
     */
    @Override
    public Problem getProblemById(Long problemId) {
        return super.getById(problemId);
    }

    /**
     * 增加ProblemVO
     *
     * @param problemDTO
     * @return
     */
    @Override
    public ProblemDTO insertOneProblemVO(ProblemDTO problemDTO) {
        initAdd(problemDTO);
//        首先转换成Problem
        Problem problem = problemDTO.unzipProblemVO();
        problemMapper.insert(problem);
//        添加sample
        for (Sample sample : problemDTO.getSamples()) {
            sample.setProblemId(problem.getProblemId());
            sampleMapper.insert(sample);
        }
        problemDTO.zipProblem(problem);
        return problemDTO;
    }

    /**
     * 根据题目id删除题目
     *
     * @param problemId
     * @return
     */
    @Override
    public boolean removeById(Long problemId) {
        return super.removeById(problemId);
    }

    /**
     * 根据题目id修改题目信息
     *
     * @param problem
     * @param problemId
     * @return
     */
    @Override
    public boolean update(Problem problem, Long problemId) {
        return super.update(problem, new UpdateWrapper<Problem>().eq("problem_id", problemId));
    }

    /**
     * 根据题目id获取语言id的list
     *
     * @param problemId
     * @return
     */
    @Override
    public List<Integer> getLanguageIdListByProblemId(Long problemId) {
        return problemMapper.selectLanguageIdListByProblemId(problemId);
    }

    /**
     * 更新problemDTO
     *
     * @param problemDTO
     * @return
     */
    @Override
    public ProblemDTO updateProblemDTO(ProblemDTO problemDTO) {
//        首先转换成Problem
        Problem problem = problemDTO.unzipProblemVO();
        problemMapper.updateById(problem);
//        更新sample
        // 首先删除所有的sample
        sampleMapper.delete(new QueryWrapper<Sample>().eq("problem_id",problemDTO.getProblemId()));
        for (Sample sample : problemDTO.getSamples()) {
            sample.setProblemId(problem.getProblemId());
            //添加sample
            sampleMapper.insert(sample);
        }
        problemDTO.zipProblem(problem);
        return problemDTO;
    }

    /**
     * 添加题目时初始化信息
     * @param problemDTO
     */
    private void initAdd(ProblemDTO problemDTO){
        problemDTO.setProblemCreateTime(new Date());
        // 初始化状态
        if (problemDTO.getVisible() == null) {
            problemDTO.setVisible(true);
        }
    }
}

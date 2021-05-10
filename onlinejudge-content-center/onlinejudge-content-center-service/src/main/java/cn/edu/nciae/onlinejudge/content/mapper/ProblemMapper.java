package cn.edu.nciae.onlinejudge.content.mapper;

import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.content.domain.Problem
 */
public interface ProblemMapper extends BaseMapper<Problem> {
    /**
     * 查询题目分页列表
     * @param page
     * @return
     */
//    @Param("problemParam")
    IPage<ProblemDTO> selectProblemVOListPage(Page page, ProblemParam problemParam);

    /**
     * 查询题目
     * @param problemId
     * @return
     */
    ProblemDTO selectProblemVOByPid(Long problemId);

    /**
     * 根据问题id查询样例列表
     * @param problemId
     * @return
     */
    List<Sample> selectSampleListByProblemId(Long problemId);

    /**
     * 根据问题id查询列表
     * @param problemId
     * @return
     */
    List<Tag> selectTagListByProblemId(Long problemId);

    /**
     * 根据问题id查找编程语言名称
     * @param problemId
     * @return
     */
    List<Integer> selectLanguageIdListByProblemId(Long problemId);
}





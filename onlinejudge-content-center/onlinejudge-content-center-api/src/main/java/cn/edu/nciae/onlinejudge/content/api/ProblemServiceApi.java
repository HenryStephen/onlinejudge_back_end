package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:28
 */
public interface ProblemServiceApi {

    /**
     * 查询题目分页列表
     * @param page
     * @return
     */
    IPage<ProblemDTO> getProblemListPage(Page page, ProblemParam problemParam);

    /**
     * 查询题目分页列表（管理员）
     * @param page
     * @param problemParam
     * @return
     */
    IPage<ProblemDTO> getProblemListPageAdmin(Page page, ProblemParam problemParam);

    /**
     * 查询题目
     * @param problemId
     * @return
     */
    ProblemDTO getProblemVOByPid(Long problemId);

    /**
     * 根据id查找题目
     * @param problemId
     * @return
     */
    Problem getProblemById(Long problemId);

    /**
     * 增加ProblemVO
     * @param problemDTO
     * @return
     */
    ProblemDTO insertOneProblemVO(ProblemDTO problemDTO);

    /**
     * 根据题目id删除题目
     * @param problemId
     * @return
     */
    boolean removeById(Long problemId);

    /**
     * 根据题目id修改题目信息
     * @param problem
     * @param problemId
     * @return
     */
    boolean update(Problem problem, Long problemId);

    /**
     * 根据题目id获取语言id的list
     * @param problemId
     * @return
     */
    List<Integer> getLanguageIdListByProblemId(Long problemId);

    /**
     * 更新problemDTO
     * @param problemDTO
     * @return
     */
	ProblemDTO updateProblemDTO(ProblemDTO problemDTO);
}

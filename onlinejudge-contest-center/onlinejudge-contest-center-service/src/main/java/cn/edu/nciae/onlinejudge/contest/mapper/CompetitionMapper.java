package cn.edu.nciae.onlinejudge.contest.mapper;

import cn.edu.nciae.onlinejudge.contest.domain.Competition;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionDTO;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Entity cn.edu.nciae.onlinejudge.contest.domain.Competition
 */
public interface CompetitionMapper extends BaseMapper<Competition> {
    /**
     * 根据参数查询竞赛列表
     * @param page
     * @param competitionParam
     * @return
     */
    IPage<CompetitionDTO> selectCompetitionVOListPage(Page page, CompetitionParam competitionParam);

    /**
     * 根据参数查询竞赛列表(管理员)
     * @param page
     * @param competitionParam
     * @return
     */
    IPage<CompetitionDTO> selectCompetitionVOListPageAdmin(Page page, CompetitionParam competitionParam);

    /**
     * 根据竞赛id查找竞赛信息
     * @param competitionId
     * @return
     */
    CompetitionDTO selectCompetitionVOById(Long competitionId);

}





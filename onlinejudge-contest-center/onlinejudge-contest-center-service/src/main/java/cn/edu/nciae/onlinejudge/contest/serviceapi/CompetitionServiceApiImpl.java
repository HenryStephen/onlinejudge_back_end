package cn.edu.nciae.onlinejudge.contest.serviceapi;

import cn.edu.nciae.onlinejudge.contest.api.CompetitionServiceApi;
import cn.edu.nciae.onlinejudge.contest.mapper.CompetitionMapper;
import cn.edu.nciae.onlinejudge.contest.service.impl.CompetitionServiceImpl;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionDTO;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 9:48
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class CompetitionServiceApiImpl extends CompetitionServiceImpl implements CompetitionServiceApi {

    @Autowired
    private CompetitionMapper competitionMapper;

    /**
     * 根据参数查询竞赛列表
     *
     * @param page
     * @param competitionParam
     * @return
     */
    @Override
    public IPage<CompetitionDTO> getCompetitionListPage(Page page, CompetitionParam competitionParam) {
        return competitionMapper.selectCompetitionVOListPage(page, competitionParam);
    }

    /**
     * 根据id查找具体竞赛
     *
     * @param competitionId
     * @return
     */
    @Override
    public CompetitionDTO getCompetitionVOById(Long competitionId) {
        return competitionMapper.selectCompetitionVOById(competitionId);
    }
}

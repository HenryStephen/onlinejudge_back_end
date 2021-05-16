package cn.edu.nciae.onlinejudge.contest.serviceapi;

import cn.edu.nciae.onlinejudge.contest.api.CompetitionServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.Competition;
import cn.edu.nciae.onlinejudge.contest.mapper.CompetitionMapper;
import cn.edu.nciae.onlinejudge.contest.service.impl.CompetitionServiceImpl;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionDTO;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionParam;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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

    /**
     * 更改竞赛信息
     *
     * @param competition
     * @param competitionId
     * @return
     */
    @Override
    public boolean update(Competition competition, Long competitionId) {
        return super.update(competition, new UpdateWrapper<Competition>().eq("competition_id", competitionId));
    }

    /**
     * 添加竞赛
     * @param competition
     * @return
     */
    @Override
    public boolean save(Competition competition){
        initAdd(competition);
        return super.save(competition);
    }

    /**
     * 添加竞赛时初始化的信息
     * @param competition
     */
    private void initAdd(Competition competition){
        competition.setCompetitionCreateTime(new Date());
        if(competition.getCompetitionPassword() == null){
            competition.setCompetitionType("Public");
        }else{
            competition.setCompetitionType("Password Protected");
        }
        // 初始化状态
        if (competition.getVisible() == null) {
            competition.setVisible(true);
        }
    }
}

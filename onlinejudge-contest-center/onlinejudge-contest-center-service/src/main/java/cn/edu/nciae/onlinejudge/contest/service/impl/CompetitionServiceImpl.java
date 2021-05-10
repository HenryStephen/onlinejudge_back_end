package cn.edu.nciae.onlinejudge.contest.service.impl;

import cn.edu.nciae.onlinejudge.contest.domain.Competition;
import cn.edu.nciae.onlinejudge.contest.mapper.CompetitionMapper;
import cn.edu.nciae.onlinejudge.contest.service.CompetitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 11:42
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition>
        implements CompetitionService {

}

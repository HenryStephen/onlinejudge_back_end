package cn.edu.nciae.onlinejudge.contest.vo;

import cn.edu.nciae.onlinejudge.contest.domain.Competition;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 9:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "competition", resultMap = "CompetitionViewResultMap")
public class CompetitionDTO extends Competition {

    /**
     * 发起人
     */
    private String createUserName;

    /**
     * 当前时间
     */
    private Date now;

    /**
     * 竞赛的当前状态
     * 'NOT_START': '1',
     * 'UNDERWAY': '0',
     * 'ENDED': '-1'
     */
    private Integer competitionStatus;
}

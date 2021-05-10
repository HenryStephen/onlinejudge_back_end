package cn.edu.nciae.onlinejudge.contest.vo;

import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 16:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "competition_problem", resultMap = "CompetitionProblemViewResultMap")
public class CompetitionProblemDTO extends CompetitionProblem {

    /**
     * 题目名称
     */
    private String problemTiltle;


}

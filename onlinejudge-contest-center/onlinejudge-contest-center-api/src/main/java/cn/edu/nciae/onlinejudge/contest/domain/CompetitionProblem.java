package cn.edu.nciae.onlinejudge.contest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName competition_problem
 */
@TableName(value ="competition_problem")
@Data
public class CompetitionProblem implements Serializable {
    /**
     * 竞赛-题目 id
     */
    @TableId(value = "competition_problem_id", type = IdType.AUTO)
    private Long competitionProblemId;

    /**
     * 竞赛id
     */
    @TableField(value = "competition_id")
    private Long competitionId;

    /**
     * 题目id
     */
    @TableField(value = "problem_id")
    private Long problemId;

    /**
     * 题目展示id
     */
    @TableField(value = "problem_display_id")
    private Long problemDisplayId;

    /**
     * 题目所占分数
     */
    @TableField(value = "problem_score")
    private Long problemScore;

    /**
     * 提交人数
     */
    @TableField(value = "submit_number")
    private Integer submitNumber;

    /**
     * 通过人数
     */
    @TableField(value = "solved_number")
    private Integer solvedNumber;

    /**
     * 赛制
     */
    @TableField(value = "problem_rule_type")
    private String problemRuleType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CompetitionProblem other = (CompetitionProblem) that;
        return (this.getCompetitionProblemId() == null ? other.getCompetitionProblemId() == null : this.getCompetitionProblemId().equals(other.getCompetitionProblemId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getProblemDisplayId() == null ? other.getProblemDisplayId() == null : this.getProblemDisplayId().equals(other.getProblemDisplayId()))
            && (this.getProblemScore() == null ? other.getProblemScore() == null : this.getProblemScore().equals(other.getProblemScore()))
            && (this.getSubmitNumber() == null ? other.getSubmitNumber() == null : this.getSubmitNumber().equals(other.getSubmitNumber()))
            && (this.getSolvedNumber() == null ? other.getSolvedNumber() == null : this.getSolvedNumber().equals(other.getSolvedNumber()))
            && (this.getProblemRuleType() == null ? other.getProblemRuleType() == null : this.getProblemRuleType().equals(other.getProblemRuleType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompetitionProblemId() == null) ? 0 : getCompetitionProblemId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getProblemDisplayId() == null) ? 0 : getProblemDisplayId().hashCode());
        result = prime * result + ((getProblemScore() == null) ? 0 : getProblemScore().hashCode());
        result = prime * result + ((getSubmitNumber() == null) ? 0 : getSubmitNumber().hashCode());
        result = prime * result + ((getSolvedNumber() == null) ? 0 : getSolvedNumber().hashCode());
        result = prime * result + ((getProblemRuleType() == null) ? 0 : getProblemRuleType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", competitionProblemId=").append(competitionProblemId);
        sb.append(", competitionId=").append(competitionId);
        sb.append(", problemId=").append(problemId);
        sb.append(", problemDisplayId=").append(problemDisplayId);
        sb.append(", problemScore=").append(problemScore);
        sb.append(", submitNumber=").append(submitNumber);
        sb.append(", solvedNumber=").append(solvedNumber);
        sb.append(", problemRuleType=").append(problemRuleType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
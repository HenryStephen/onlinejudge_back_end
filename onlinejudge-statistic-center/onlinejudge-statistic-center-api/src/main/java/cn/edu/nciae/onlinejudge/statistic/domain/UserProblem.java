package cn.edu.nciae.onlinejudge.statistic.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_problem
 */
@TableName(value ="user_problem")
@Data
public class UserProblem implements Serializable {
    /**
     * 关联关系id
     */
    @TableId(value = "user_problem_id", type = IdType.AUTO)
    private Long userProblemId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 题目id
     */
    @TableField(value = "problem_display_id")
    private Long problemDisplayId;

    /**
     * 竞赛id
     */
    @TableField(value = "competition_id")
    private Long competitionId;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

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
        UserProblem other = (UserProblem) that;
        return (this.getUserProblemId() == null ? other.getUserProblemId() == null : this.getUserProblemId().equals(other.getUserProblemId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getProblemDisplayId() == null ? other.getProblemDisplayId() == null : this.getProblemDisplayId().equals(other.getProblemDisplayId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserProblemId() == null) ? 0 : getUserProblemId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProblemDisplayId() == null) ? 0 : getProblemDisplayId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userProblemId=").append(userProblemId);
        sb.append(", userId=").append(userId);
        sb.append(", problemDisplayId=").append(problemDisplayId);
        sb.append(", competitionId=").append(competitionId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
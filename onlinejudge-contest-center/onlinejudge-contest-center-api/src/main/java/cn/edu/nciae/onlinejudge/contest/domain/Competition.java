package cn.edu.nciae.onlinejudge.contest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 * @TableName competition
 */
@TableName(value ="competition")
@Data
public class Competition implements Serializable {
    /**
     * 竞赛id
     */
    @TableId(value = "competition_id", type = IdType.AUTO)
    private Long competitionId;

    /**
     * 创建的用户id
     */
    @TableField(value = "competition_create_user")
    private Long competitionCreateUser;

    /**
     * 竞赛标题
     */
    @TableField(value = "competition_title")
    private String competitionTitle;

    /**
     * 竞赛描述
     */
    @TableField(value = "competition_description")
    private String competitionDescription;

    /**
     * 竞赛赛制(OI ACM)
     */
    @TableField(value = "competition_rule_type")
    private String competitionRuleType;

    /**
     * 竞赛类型(Public , Password Protected)
     */
    @TableField(value = "competition_type")
    private String competitionType;

    /**
     * 竞赛类型为密码保护时，才需要此密码
     */
    @TableField(value = "competition_password")
    private String competitionPassword;

    /**
     * 竞赛榜单权限(针对于OI模式)
     */
    @TableField(value = "competition_real_time_rank")
    private Boolean competitionRealTimeRank;

    /**
     * 竞赛创建时间
     */
    @TableField(value = "competition_create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date competitionCreateTime;

    /**
     * 竞赛开始时间
     */
    @TableField(value = "competition_start_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date competitionStartTime;

    /**
     * 竞赛结束时间
     */
    @TableField(value = "competition_end_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date competitionEndTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

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
        Competition other = (Competition) that;
        return (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getCompetitionCreateUser() == null ? other.getCompetitionCreateUser() == null : this.getCompetitionCreateUser().equals(other.getCompetitionCreateUser()))
            && (this.getCompetitionTitle() == null ? other.getCompetitionTitle() == null : this.getCompetitionTitle().equals(other.getCompetitionTitle()))
            && (this.getCompetitionDescription() == null ? other.getCompetitionDescription() == null : this.getCompetitionDescription().equals(other.getCompetitionDescription()))
            && (this.getCompetitionRuleType() == null ? other.getCompetitionRuleType() == null : this.getCompetitionRuleType().equals(other.getCompetitionRuleType()))
            && (this.getCompetitionType() == null ? other.getCompetitionType() == null : this.getCompetitionType().equals(other.getCompetitionType()))
            && (this.getCompetitionPassword() == null ? other.getCompetitionPassword() == null : this.getCompetitionPassword().equals(other.getCompetitionPassword()))
            && (this.getCompetitionRealTimeRank() == null ? other.getCompetitionRealTimeRank() == null : this.getCompetitionRealTimeRank().equals(other.getCompetitionRealTimeRank()))
            && (this.getCompetitionCreateTime() == null ? other.getCompetitionCreateTime() == null : this.getCompetitionCreateTime().equals(other.getCompetitionCreateTime()))
            && (this.getCompetitionStartTime() == null ? other.getCompetitionStartTime() == null : this.getCompetitionStartTime().equals(other.getCompetitionStartTime()))
            && (this.getCompetitionEndTime() == null ? other.getCompetitionEndTime() == null : this.getCompetitionEndTime().equals(other.getCompetitionEndTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getCompetitionCreateUser() == null) ? 0 : getCompetitionCreateUser().hashCode());
        result = prime * result + ((getCompetitionTitle() == null) ? 0 : getCompetitionTitle().hashCode());
        result = prime * result + ((getCompetitionDescription() == null) ? 0 : getCompetitionDescription().hashCode());
        result = prime * result + ((getCompetitionRuleType() == null) ? 0 : getCompetitionRuleType().hashCode());
        result = prime * result + ((getCompetitionType() == null) ? 0 : getCompetitionType().hashCode());
        result = prime * result + ((getCompetitionPassword() == null) ? 0 : getCompetitionPassword().hashCode());
        result = prime * result + ((getCompetitionRealTimeRank() == null) ? 0 : getCompetitionRealTimeRank().hashCode());
        result = prime * result + ((getCompetitionCreateTime() == null) ? 0 : getCompetitionCreateTime().hashCode());
        result = prime * result + ((getCompetitionStartTime() == null) ? 0 : getCompetitionStartTime().hashCode());
        result = prime * result + ((getCompetitionEndTime() == null) ? 0 : getCompetitionEndTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", competitionId=").append(competitionId);
        sb.append(", competitionCreateUser=").append(competitionCreateUser);
        sb.append(", competitionTitle=").append(competitionTitle);
        sb.append(", competitionDescription=").append(competitionDescription);
        sb.append(", competitionRuleType=").append(competitionRuleType);
        sb.append(", competitionType=").append(competitionType);
        sb.append(", competitionPassword=").append(competitionPassword);
        sb.append(", competitionRealTimeRank=").append(competitionRealTimeRank);
        sb.append(", competitionCreateTime=").append(competitionCreateTime);
        sb.append(", competitionStartTime=").append(competitionStartTime);
        sb.append(", competitionEndTime=").append(competitionEndTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

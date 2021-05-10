package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName announcement
 */
@TableName(value ="announcement")
@Data
public class Announcement implements Serializable {
    /**
     * 公告id
     */
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Long announcementId;

    /**
     * 竞赛id
     */
    @TableField(value = "competition_id")
    private Long competitionId;

    /**
     * 创建人用户名
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 公告标题
     */
    @TableField(value = "announcement_name")
    private String announcementName;

    /**
     * 公告内容
     */
    @TableField(value = "announcement_content")
    private String announcementContent;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 2698839643451231403L;


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
        Announcement other = (Announcement) that;
        return (this.getAnnouncementId() == null ? other.getAnnouncementId() == null : this.getAnnouncementId().equals(other.getAnnouncementId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getAnnouncementName() == null ? other.getAnnouncementName() == null : this.getAnnouncementName().equals(other.getAnnouncementName()))
            && (this.getAnnouncementContent() == null ? other.getAnnouncementContent() == null : this.getAnnouncementContent().equals(other.getAnnouncementContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnnouncementId() == null) ? 0 : getAnnouncementId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getAnnouncementName() == null) ? 0 : getAnnouncementName().hashCode());
        result = prime * result + ((getAnnouncementContent() == null) ? 0 : getAnnouncementContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", announcementId=").append(announcementId);
        sb.append(", competitionId=").append(competitionId);
        sb.append(", nickname=").append(nickname);
        sb.append(", announcementName=").append(announcementName);
        sb.append(", announcementContent=").append(announcementContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

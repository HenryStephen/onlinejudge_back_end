package cn.edu.nciae.onlinejudge.user.domain;

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
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户真实姓名
     */
    @TableField(value = "user_real_name")
    private String userRealName;

    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 注册时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "user_regtime")
    private Date userRegtime;

    /**
     * 解题数量
     */
    @TableField(value = "user_solve_number")
    private Integer userSolveNumber;

    /**
     * 提交次数
     */
    @TableField(value = "user_submission_number")
    private Integer userSubmissionNumber;

    /**
     * 用户总得分
     */
    @TableField(value = "user_total_score")
    private Integer userTotalScore;

    /**
     * 界面语言
     */
    @TableField(value = "user_language")
    private String userLanguage;

    /**
     * 学校
     */
    @TableField(value = "user_school")
    private String userSchool;

    /**
     * 心情
     */
    @TableField(value = "user_mood")
    private String userMood;

    /**
     * 博客
     */
    @TableField(value = "user_blog")
    private String userBlog;

    /**
     * Base64头像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * OSS头像URL
     */
    @TableField(value = "user_avatar_url")
    private String userAvatarUrl;

    /**
     * Github Url
     */
    @TableField(value = "user_github")
    private String userGithub;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    /**
     * 是否禁用
     */
    @TableField(value = "is_disable")
    private Boolean isDisable;

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
        UserInfo other = (UserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserRealName() == null ? other.getUserRealName() == null : this.getUserRealName().equals(other.getUserRealName()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserRegtime() == null ? other.getUserRegtime() == null : this.getUserRegtime().equals(other.getUserRegtime()))
            && (this.getUserSolveNumber() == null ? other.getUserSolveNumber() == null : this.getUserSolveNumber().equals(other.getUserSolveNumber()))
            && (this.getUserSubmissionNumber() == null ? other.getUserSubmissionNumber() == null : this.getUserSubmissionNumber().equals(other.getUserSubmissionNumber()))
            && (this.getUserTotalScore() == null ? other.getUserTotalScore() == null : this.getUserTotalScore().equals(other.getUserTotalScore()))
            && (this.getUserLanguage() == null ? other.getUserLanguage() == null : this.getUserLanguage().equals(other.getUserLanguage()))
            && (this.getUserSchool() == null ? other.getUserSchool() == null : this.getUserSchool().equals(other.getUserSchool()))
            && (this.getUserMood() == null ? other.getUserMood() == null : this.getUserMood().equals(other.getUserMood()))
            && (this.getUserBlog() == null ? other.getUserBlog() == null : this.getUserBlog().equals(other.getUserBlog()))
            && (this.getUserAvatar() == null ? other.getUserAvatar() == null : this.getUserAvatar().equals(other.getUserAvatar()))
            && (this.getUserAvatarUrl() == null ? other.getUserAvatarUrl() == null : this.getUserAvatarUrl().equals(other.getUserAvatarUrl()))
            && (this.getUserGithub() == null ? other.getUserGithub() == null : this.getUserGithub().equals(other.getUserGithub()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getIsDisable() == null ? other.getIsDisable() == null : this.getIsDisable().equals(other.getIsDisable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserRealName() == null) ? 0 : getUserRealName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserRegtime() == null) ? 0 : getUserRegtime().hashCode());
        result = prime * result + ((getUserSolveNumber() == null) ? 0 : getUserSolveNumber().hashCode());
        result = prime * result + ((getUserSubmissionNumber() == null) ? 0 : getUserSubmissionNumber().hashCode());
        result = prime * result + ((getUserTotalScore() == null) ? 0 : getUserTotalScore().hashCode());
        result = prime * result + ((getUserLanguage() == null) ? 0 : getUserLanguage().hashCode());
        result = prime * result + ((getUserSchool() == null) ? 0 : getUserSchool().hashCode());
        result = prime * result + ((getUserMood() == null) ? 0 : getUserMood().hashCode());
        result = prime * result + ((getUserBlog() == null) ? 0 : getUserBlog().hashCode());
        result = prime * result + ((getUserAvatar() == null) ? 0 : getUserAvatar().hashCode());
        result = prime * result + ((getUserAvatarUrl() == null) ? 0 : getUserAvatarUrl().hashCode());
        result = prime * result + ((getUserGithub() == null) ? 0 : getUserGithub().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getIsDisable() == null) ? 0 : getIsDisable().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userRealName=").append(userRealName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userRegtime=").append(userRegtime);
        sb.append(", userSolveNumber=").append(userSolveNumber);
        sb.append(", userSubmissionNumber=").append(userSubmissionNumber);
        sb.append(", userTotalScore=").append(userTotalScore);
        sb.append(", userLanguage=").append(userLanguage);
        sb.append(", userSchool=").append(userSchool);
        sb.append(", userMood=").append(userMood);
        sb.append(", userBlog=").append(userBlog);
        sb.append(", userAvatar=").append(userAvatar);
        sb.append(", userAvatarUrl=").append(userAvatarUrl);
        sb.append(", userGithub=").append(userGithub);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", isDisable=").append(isDisable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
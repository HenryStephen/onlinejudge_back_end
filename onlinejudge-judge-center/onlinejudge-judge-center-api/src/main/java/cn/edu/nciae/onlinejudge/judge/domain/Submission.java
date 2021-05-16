package cn.edu.nciae.onlinejudge.judge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName submission
 */
@TableName(value ="submission")
@Data
public class Submission implements Serializable {
    /**
     * 提交记录id
     */
    @TableId(value = "submission_id", type = IdType.INPUT)
    private String submissionId;

    /**
     * 提交用户id
     */
    @TableField(value = "submission_user_id")
    private Long submissionUserId;

    /**
     * 题目id
     */
    @TableField(value = "submission_problem_id")
    private Long submissionProblemId;

    /**
     * 竞赛id
     */
    @TableField(value = "submission_contest_id")
    private Long submissionContestId;

    /**
     * 解题使用语言id
     */
    @TableField(value = "submission_language_id")
    private Integer submissionLanguageId;

    /**
     * 提交源码
     */
    @TableField(value = "submission_source_code")
    private String submissionSourceCode;

    /**
     * 提交时间
     */
    @TableField(value = "submission_commit_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date submissionCommitTime;

    /**
     * 判题状态
     */
    @TableField(value = "submission_status")
    private Integer submissionStatus;

    /**
     * 运行时间(MS)
     */
    @TableField(value = "submission_used_time")
    private Integer submissionUsedTime;

    /**
     * 运行所需内存大小(KB)
     */
    @TableField(value = "submission_used_memory")
    private Integer submissionUsedMemory;

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
        Submission other = (Submission) that;
        return (this.getSubmissionId() == null ? other.getSubmissionId() == null : this.getSubmissionId().equals(other.getSubmissionId()))
            && (this.getSubmissionUserId() == null ? other.getSubmissionUserId() == null : this.getSubmissionUserId().equals(other.getSubmissionUserId()))
            && (this.getSubmissionProblemId() == null ? other.getSubmissionProblemId() == null : this.getSubmissionProblemId().equals(other.getSubmissionProblemId()))
            && (this.getSubmissionContestId() == null ? other.getSubmissionContestId() == null : this.getSubmissionContestId().equals(other.getSubmissionContestId()))
            && (this.getSubmissionLanguageId() == null ? other.getSubmissionLanguageId() == null : this.getSubmissionLanguageId().equals(other.getSubmissionLanguageId()))
            && (this.getSubmissionSourceCode() == null ? other.getSubmissionSourceCode() == null : this.getSubmissionSourceCode().equals(other.getSubmissionSourceCode()))
            && (this.getSubmissionCommitTime() == null ? other.getSubmissionCommitTime() == null : this.getSubmissionCommitTime().equals(other.getSubmissionCommitTime()))
            && (this.getSubmissionStatus() == null ? other.getSubmissionStatus() == null : this.getSubmissionStatus().equals(other.getSubmissionStatus()))
            && (this.getSubmissionUsedTime() == null ? other.getSubmissionUsedTime() == null : this.getSubmissionUsedTime().equals(other.getSubmissionUsedTime()))
            && (this.getSubmissionUsedMemory() == null ? other.getSubmissionUsedMemory() == null : this.getSubmissionUsedMemory().equals(other.getSubmissionUsedMemory()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSubmissionId() == null) ? 0 : getSubmissionId().hashCode());
        result = prime * result + ((getSubmissionUserId() == null) ? 0 : getSubmissionUserId().hashCode());
        result = prime * result + ((getSubmissionProblemId() == null) ? 0 : getSubmissionProblemId().hashCode());
        result = prime * result + ((getSubmissionContestId() == null) ? 0 : getSubmissionContestId().hashCode());
        result = prime * result + ((getSubmissionLanguageId() == null) ? 0 : getSubmissionLanguageId().hashCode());
        result = prime * result + ((getSubmissionSourceCode() == null) ? 0 : getSubmissionSourceCode().hashCode());
        result = prime * result + ((getSubmissionCommitTime() == null) ? 0 : getSubmissionCommitTime().hashCode());
        result = prime * result + ((getSubmissionStatus() == null) ? 0 : getSubmissionStatus().hashCode());
        result = prime * result + ((getSubmissionUsedTime() == null) ? 0 : getSubmissionUsedTime().hashCode());
        result = prime * result + ((getSubmissionUsedMemory() == null) ? 0 : getSubmissionUsedMemory().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", submissionId=").append(submissionId);
        sb.append(", submissionUserId=").append(submissionUserId);
        sb.append(", submissionProblemId=").append(submissionProblemId);
        sb.append(", submissionContestId=").append(submissionContestId);
        sb.append(", submissionLanguageId=").append(submissionLanguageId);
        sb.append(", submissionSourceCode=").append(submissionSourceCode);
        sb.append(", submissionCommitTime=").append(submissionCommitTime);
        sb.append(", submissionStatus=").append(submissionStatus);
        sb.append(", submissionUsedTime=").append(submissionUsedTime);
        sb.append(", submissionUsedMemory=").append(submissionUsedMemory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

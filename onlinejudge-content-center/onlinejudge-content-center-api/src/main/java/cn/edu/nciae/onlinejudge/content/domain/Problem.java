package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName problem
 */
@TableName(value ="problem")
@Data
public class Problem implements Serializable {
    /**
     * 题目ID
     */
    @TableId(value = "problem_id", type = IdType.AUTO)
    private Long problemId;

    /**
     * 添加者ID
     */
    @TableField(value = "add_user_id")
    private Long addUserId;

    /**
     * 题目名称
     */
    @TableField(value = "problem_title")
    private String problemTitle;

    /**
     * 题目描述
     */
    @TableField(value = "problem_description")
    private String problemDescription;

    /**
     * 输入格式
     */
    @TableField(value = "problem_input_formation")
    private String problemInputFormation;

    /**
     * 输出格式
     */
    @TableField(value = "problem_output_formation")
    private String problemOutputFormation;

    /**
     * 时间限制(MS)
     */
    @TableField(value = "problem_time_limit")
    private Integer problemTimeLimit;

    /**
     * 内存限制(MB)
     */
    @TableField(value = "problem_memory_limit")
    private Integer problemMemoryLimit;

    /**
     * 提交次数
     */
    @TableField(value = "problem_submit_number")
    private Integer problemSubmitNumber;

    /**
     * 解决人数
     */
    @TableField(value = "problem_solved_number")
    private Integer problemSolvedNumber;

    /**
     * 题目作者
     */
    @TableField(value = "problem_author")
    private String problemAuthor;

    /**
     * 特殊判题默认否0 是1
     */
    @TableField(value = "problem_special_judge")
    private Boolean problemSpecialJudge;

    /**
     * 提示
     */
    @TableField(value = "problem_reminder")
    private String problemReminder;

    /**
     * 题目来源
     */
    @TableField(value = "problem_source")
    private String problemSource;

    /**
     * 题目难度
     */
    @TableField(value = "problem_difficulty")
    private String problemDifficulty;

    /**
     * 状态0 public，1 private
     */
    @TableField(value = "problem_status")
    private Integer problemStatus;

    /**
     * 测试用例id
     */
    @TableField(value = "problem_testcase_id")
    private String problemTestcaseId;

    /**
     * 是否为特殊判题
     */
    @TableField(value = "is_spj")
    private Boolean isSpj;

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
        Problem other = (Problem) that;
        return (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getAddUserId() == null ? other.getAddUserId() == null : this.getAddUserId().equals(other.getAddUserId()))
            && (this.getProblemTitle() == null ? other.getProblemTitle() == null : this.getProblemTitle().equals(other.getProblemTitle()))
            && (this.getProblemDescription() == null ? other.getProblemDescription() == null : this.getProblemDescription().equals(other.getProblemDescription()))
            && (this.getProblemInputFormation() == null ? other.getProblemInputFormation() == null : this.getProblemInputFormation().equals(other.getProblemInputFormation()))
            && (this.getProblemOutputFormation() == null ? other.getProblemOutputFormation() == null : this.getProblemOutputFormation().equals(other.getProblemOutputFormation()))
            && (this.getProblemTimeLimit() == null ? other.getProblemTimeLimit() == null : this.getProblemTimeLimit().equals(other.getProblemTimeLimit()))
            && (this.getProblemMemoryLimit() == null ? other.getProblemMemoryLimit() == null : this.getProblemMemoryLimit().equals(other.getProblemMemoryLimit()))
            && (this.getProblemSubmitNumber() == null ? other.getProblemSubmitNumber() == null : this.getProblemSubmitNumber().equals(other.getProblemSubmitNumber()))
            && (this.getProblemSolvedNumber() == null ? other.getProblemSolvedNumber() == null : this.getProblemSolvedNumber().equals(other.getProblemSolvedNumber()))
            && (this.getProblemAuthor() == null ? other.getProblemAuthor() == null : this.getProblemAuthor().equals(other.getProblemAuthor()))
            && (this.getProblemSpecialJudge() == null ? other.getProblemSpecialJudge() == null : this.getProblemSpecialJudge().equals(other.getProblemSpecialJudge()))
            && (this.getProblemReminder() == null ? other.getProblemReminder() == null : this.getProblemReminder().equals(other.getProblemReminder()))
            && (this.getProblemSource() == null ? other.getProblemSource() == null : this.getProblemSource().equals(other.getProblemSource()))
            && (this.getProblemDifficulty() == null ? other.getProblemDifficulty() == null : this.getProblemDifficulty().equals(other.getProblemDifficulty()))
            && (this.getProblemStatus() == null ? other.getProblemStatus() == null : this.getProblemStatus().equals(other.getProblemStatus()))
            && (this.getProblemTestcaseId() == null ? other.getProblemTestcaseId() == null : this.getProblemTestcaseId().equals(other.getProblemTestcaseId()))
            && (this.getIsSpj() == null ? other.getIsSpj() == null : this.getIsSpj().equals(other.getIsSpj()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getAddUserId() == null) ? 0 : getAddUserId().hashCode());
        result = prime * result + ((getProblemTitle() == null) ? 0 : getProblemTitle().hashCode());
        result = prime * result + ((getProblemDescription() == null) ? 0 : getProblemDescription().hashCode());
        result = prime * result + ((getProblemInputFormation() == null) ? 0 : getProblemInputFormation().hashCode());
        result = prime * result + ((getProblemOutputFormation() == null) ? 0 : getProblemOutputFormation().hashCode());
        result = prime * result + ((getProblemTimeLimit() == null) ? 0 : getProblemTimeLimit().hashCode());
        result = prime * result + ((getProblemMemoryLimit() == null) ? 0 : getProblemMemoryLimit().hashCode());
        result = prime * result + ((getProblemSubmitNumber() == null) ? 0 : getProblemSubmitNumber().hashCode());
        result = prime * result + ((getProblemSolvedNumber() == null) ? 0 : getProblemSolvedNumber().hashCode());
        result = prime * result + ((getProblemAuthor() == null) ? 0 : getProblemAuthor().hashCode());
        result = prime * result + ((getProblemSpecialJudge() == null) ? 0 : getProblemSpecialJudge().hashCode());
        result = prime * result + ((getProblemReminder() == null) ? 0 : getProblemReminder().hashCode());
        result = prime * result + ((getProblemSource() == null) ? 0 : getProblemSource().hashCode());
        result = prime * result + ((getProblemDifficulty() == null) ? 0 : getProblemDifficulty().hashCode());
        result = prime * result + ((getProblemStatus() == null) ? 0 : getProblemStatus().hashCode());
        result = prime * result + ((getProblemTestcaseId() == null) ? 0 : getProblemTestcaseId().hashCode());
        result = prime * result + ((getIsSpj() == null) ? 0 : getIsSpj().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", problemId=").append(problemId);
        sb.append(", addUserId=").append(addUserId);
        sb.append(", problemTitle=").append(problemTitle);
        sb.append(", problemDescription=").append(problemDescription);
        sb.append(", problemInputFormation=").append(problemInputFormation);
        sb.append(", problemOutputFormation=").append(problemOutputFormation);
        sb.append(", problemTimeLimit=").append(problemTimeLimit);
        sb.append(", problemMemoryLimit=").append(problemMemoryLimit);
        sb.append(", problemSubmitNumber=").append(problemSubmitNumber);
        sb.append(", problemSolvedNumber=").append(problemSolvedNumber);
        sb.append(", problemAuthor=").append(problemAuthor);
        sb.append(", problemSpecialJudge=").append(problemSpecialJudge);
        sb.append(", problemReminder=").append(problemReminder);
        sb.append(", problemSource=").append(problemSource);
        sb.append(", problemDifficulty=").append(problemDifficulty);
        sb.append(", problemStatus=").append(problemStatus);
        sb.append(", problemTestcaseId=").append(problemTestcaseId);
        sb.append(", isSpj=").append(isSpj);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

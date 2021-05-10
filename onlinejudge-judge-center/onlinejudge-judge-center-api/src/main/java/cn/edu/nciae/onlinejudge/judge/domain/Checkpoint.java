package cn.edu.nciae.onlinejudge.judge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName checkpoint
 */
@TableName(value ="checkpoint")
@Data
public class Checkpoint implements Serializable {
    /**
     * 检查点ID
     */
    @TableId(value = "checkpoint_id", type = IdType.AUTO)
    private Integer checkpointId;

    /**
     * 题目ID与内容中心同步
     */
    @TableField(value = "problem_id")
    private Long problemId;

    /**
     * 标准输入
     */
    @TableField(value = "input")
    private String input;

    /**
     * 标准输出
     */
    @TableField(value = "output")
    private String output;

    /**
     * 逻辑删除
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
        Checkpoint other = (Checkpoint) that;
        return (this.getCheckpointId() == null ? other.getCheckpointId() == null : this.getCheckpointId().equals(other.getCheckpointId()))
            && (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getInput() == null ? other.getInput() == null : this.getInput().equals(other.getInput()))
            && (this.getOutput() == null ? other.getOutput() == null : this.getOutput().equals(other.getOutput()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCheckpointId() == null) ? 0 : getCheckpointId().hashCode());
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getInput() == null) ? 0 : getInput().hashCode());
        result = prime * result + ((getOutput() == null) ? 0 : getOutput().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkpointId=").append(checkpointId);
        sb.append(", problemId=").append(problemId);
        sb.append(", input=").append(input);
        sb.append(", output=").append(output);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
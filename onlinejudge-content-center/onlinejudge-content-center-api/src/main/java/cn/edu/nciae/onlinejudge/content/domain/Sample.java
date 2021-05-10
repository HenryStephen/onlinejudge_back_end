package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @TableName sample
 */
@TableName(value ="sample")
@Data
@Builder
public class Sample implements Serializable {

    /**
     * 样例id
     */
    @TableId(value = "sample_id", type = IdType.AUTO)
    private Long sampleId;

    /**
     * 题目id
     */
    @TableField(value = "problem_id")
    private Long problemId;

    /**
     * 样例输入
     */
    @TableField(value = "sample_input")
    private String sampleInput;

    /**
     * 样例输出
     */
    @TableField(value = "sample_output")
    private String sampleOutput;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 7481126267302662932L;

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
        Sample other = (Sample) that;
        return (this.getSampleId() == null ? other.getSampleId() == null : this.getSampleId().equals(other.getSampleId()))
            && (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getSampleInput() == null ? other.getSampleInput() == null : this.getSampleInput().equals(other.getSampleInput()))
            && (this.getSampleOutput() == null ? other.getSampleOutput() == null : this.getSampleOutput().equals(other.getSampleOutput()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSampleId() == null) ? 0 : getSampleId().hashCode());
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getSampleInput() == null) ? 0 : getSampleInput().hashCode());
        result = prime * result + ((getSampleOutput() == null) ? 0 : getSampleOutput().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sampleId=").append(sampleId);
        sb.append(", problemId=").append(problemId);
        sb.append(", sampleInput=").append(sampleInput);
        sb.append(", sampleOutput=").append(sampleOutput);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

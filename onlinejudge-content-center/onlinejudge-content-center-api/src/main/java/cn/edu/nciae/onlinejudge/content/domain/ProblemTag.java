package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName problem_tag
 */
@TableName(value ="problem_tag")
@Data
public class ProblemTag implements Serializable {
    /**
     * 问题标签关系主键
     */
    @TableId(value = "problem_tag_id", type = IdType.AUTO)
    private Long problemTagId;

    /**
     * 问题id
     */
    @TableField(value = "problem_id")
    private Long problemId;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Long tagId;

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
        ProblemTag other = (ProblemTag) that;
        return (this.getProblemTagId() == null ? other.getProblemTagId() == null : this.getProblemTagId().equals(other.getProblemTagId()))
            && (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProblemTagId() == null) ? 0 : getProblemTagId().hashCode());
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", problemTagId=").append(problemTagId);
        sb.append(", problemId=").append(problemId);
        sb.append(", tagId=").append(tagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
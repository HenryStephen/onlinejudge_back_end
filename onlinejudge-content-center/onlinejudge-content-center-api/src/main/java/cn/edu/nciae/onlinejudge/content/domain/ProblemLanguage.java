package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName problem_language
 */
@TableName(value ="problem_language")
@Data
public class ProblemLanguage implements Serializable {
    /**
     * 题目 语言 id
     */
    @TableId(value = "problem_language_id", type = IdType.AUTO)
    private Integer problemLanguageId;

    /**
     * 题目id
     */
    @TableField(value = "problem_id")
    private Long problemId;

    /**
     * 语言id
     */
    @TableField(value = "language_id")
    private Integer languageId;

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
        ProblemLanguage other = (ProblemLanguage) that;
        return (this.getProblemLanguageId() == null ? other.getProblemLanguageId() == null : this.getProblemLanguageId().equals(other.getProblemLanguageId()))
            && (this.getProblemId() == null ? other.getProblemId() == null : this.getProblemId().equals(other.getProblemId()))
            && (this.getLanguageId() == null ? other.getLanguageId() == null : this.getLanguageId().equals(other.getLanguageId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProblemLanguageId() == null) ? 0 : getProblemLanguageId().hashCode());
        result = prime * result + ((getProblemId() == null) ? 0 : getProblemId().hashCode());
        result = prime * result + ((getLanguageId() == null) ? 0 : getLanguageId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", problemLanguageId=").append(problemLanguageId);
        sb.append(", problemId=").append(problemId);
        sb.append(", languageId=").append(languageId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package cn.edu.nciae.onlinejudge.judge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName languages
 */
@TableName(value ="languages")
@Data
public class Languages implements Serializable {
    /**
     * 语言ID
     */
    @TableId(value = "language_id", type = IdType.AUTO)
    private Integer languageId;

    /**
     * 语言缩写
     */
    @TableField(value = "language_slug")
    private String languageSlug;

    /**
     * 语言名称
     */
    @TableField(value = "language_name")
    private String languageName;

    /**
     * 编译命令id
     */
    @TableField(value = "language_compile_id")
    private Integer languageCompileId;

    /**
     * 运行命令id
     */
    @TableField(value = "language_run_id")
    private Integer languageRunId;

    /**
     * 语言文件后缀
     */
    @TableField(value = "language_suffix")
    private String languageSuffix;

    /**
     * 标注
     */
    @TableField(value = "language_description")
    private String languageDescription;

    /**
     * 是否是特殊判题
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
        Languages other = (Languages) that;
        return (this.getLanguageId() == null ? other.getLanguageId() == null : this.getLanguageId().equals(other.getLanguageId()))
            && (this.getLanguageSlug() == null ? other.getLanguageSlug() == null : this.getLanguageSlug().equals(other.getLanguageSlug()))
            && (this.getLanguageName() == null ? other.getLanguageName() == null : this.getLanguageName().equals(other.getLanguageName()))
            && (this.getLanguageCompileId() == null ? other.getLanguageCompileId() == null : this.getLanguageCompileId().equals(other.getLanguageCompileId()))
            && (this.getLanguageRunId() == null ? other.getLanguageRunId() == null : this.getLanguageRunId().equals(other.getLanguageRunId()))
            && (this.getLanguageSuffix() == null ? other.getLanguageSuffix() == null : this.getLanguageSuffix().equals(other.getLanguageSuffix()))
            && (this.getLanguageDescription() == null ? other.getLanguageDescription() == null : this.getLanguageDescription().equals(other.getLanguageDescription()))
            && (this.getIsSpj() == null ? other.getIsSpj() == null : this.getIsSpj().equals(other.getIsSpj()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLanguageId() == null) ? 0 : getLanguageId().hashCode());
        result = prime * result + ((getLanguageSlug() == null) ? 0 : getLanguageSlug().hashCode());
        result = prime * result + ((getLanguageName() == null) ? 0 : getLanguageName().hashCode());
        result = prime * result + ((getLanguageCompileId() == null) ? 0 : getLanguageCompileId().hashCode());
        result = prime * result + ((getLanguageRunId() == null) ? 0 : getLanguageRunId().hashCode());
        result = prime * result + ((getLanguageSuffix() == null) ? 0 : getLanguageSuffix().hashCode());
        result = prime * result + ((getLanguageDescription() == null) ? 0 : getLanguageDescription().hashCode());
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
        sb.append(", languageId=").append(languageId);
        sb.append(", languageSlug=").append(languageSlug);
        sb.append(", languageName=").append(languageName);
        sb.append(", languageCompileId=").append(languageCompileId);
        sb.append(", languageRunId=").append(languageRunId);
        sb.append(", languageSuffix=").append(languageSuffix);
        sb.append(", languageDescription=").append(languageDescription);
        sb.append(", isSpj=").append(isSpj);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
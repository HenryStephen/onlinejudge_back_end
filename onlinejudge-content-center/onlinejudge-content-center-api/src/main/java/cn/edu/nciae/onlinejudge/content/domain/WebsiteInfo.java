package cn.edu.nciae.onlinejudge.content.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName website_info
 */
@TableName(value ="website_info")
@Data
public class WebsiteInfo implements Serializable {
    /**
     * 网站基本url
     */
    @TableField(value = "website_base_url")
    private String websiteBaseUrl;

    /**
     * 网站名字
     */
    @TableField(value = "website_name")
    private String websiteName;

    /**
     * 网站简称
     */
    @TableField(value = "website_name_shortcut")
    private String websiteNameShortcut;

    /**
     * 页脚
     */
    @TableField(value = "website_footer")
    private String websiteFooter;

    /**
     * 网站作者
     */
    @TableField(value = "website_author")
    private String websiteAuthor;

    /**
     * 是否允许注册
     */
    @TableField(value = "allow_registry")
    private Boolean allowRegistry;

    /**
     * 是否显示全部题目提交
     */
    @TableField(value = "submission_list_show_all")
    private Boolean submissionListShowAll;

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
        WebsiteInfo other = (WebsiteInfo) that;
        return (this.getWebsiteBaseUrl() == null ? other.getWebsiteBaseUrl() == null : this.getWebsiteBaseUrl().equals(other.getWebsiteBaseUrl()))
            && (this.getWebsiteName() == null ? other.getWebsiteName() == null : this.getWebsiteName().equals(other.getWebsiteName()))
            && (this.getWebsiteNameShortcut() == null ? other.getWebsiteNameShortcut() == null : this.getWebsiteNameShortcut().equals(other.getWebsiteNameShortcut()))
            && (this.getWebsiteFooter() == null ? other.getWebsiteFooter() == null : this.getWebsiteFooter().equals(other.getWebsiteFooter()))
            && (this.getWebsiteAuthor() == null ? other.getWebsiteAuthor() == null : this.getWebsiteAuthor().equals(other.getWebsiteAuthor()))
            && (this.getAllowRegistry() == null ? other.getAllowRegistry() == null : this.getAllowRegistry().equals(other.getAllowRegistry()))
            && (this.getSubmissionListShowAll() == null ? other.getSubmissionListShowAll() == null : this.getSubmissionListShowAll().equals(other.getSubmissionListShowAll()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWebsiteBaseUrl() == null) ? 0 : getWebsiteBaseUrl().hashCode());
        result = prime * result + ((getWebsiteName() == null) ? 0 : getWebsiteName().hashCode());
        result = prime * result + ((getWebsiteNameShortcut() == null) ? 0 : getWebsiteNameShortcut().hashCode());
        result = prime * result + ((getWebsiteFooter() == null) ? 0 : getWebsiteFooter().hashCode());
        result = prime * result + ((getWebsiteAuthor() == null) ? 0 : getWebsiteAuthor().hashCode());
        result = prime * result + ((getAllowRegistry() == null) ? 0 : getAllowRegistry().hashCode());
        result = prime * result + ((getSubmissionListShowAll() == null) ? 0 : getSubmissionListShowAll().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", websiteBaseUrl=").append(websiteBaseUrl);
        sb.append(", websiteName=").append(websiteName);
        sb.append(", websiteNameShortcut=").append(websiteNameShortcut);
        sb.append(", websiteFooter=").append(websiteFooter);
        sb.append(", websiteAuthor=").append(websiteAuthor);
        sb.append(", allowRegistry=").append(allowRegistry);
        sb.append(", submissionListShowAll=").append(submissionListShowAll);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package cn.edu.nciae.onlinejudge.content.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 10:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebsiteInfoVO {
    private String websiteBaseUrl;
    private String websiteName;
    private String websiteNameShortcut;
    private String websiteFooter;
    private String webseiteAuthor;
    private Boolean allowRegistry;
    private Boolean submissionListShowAll;
}

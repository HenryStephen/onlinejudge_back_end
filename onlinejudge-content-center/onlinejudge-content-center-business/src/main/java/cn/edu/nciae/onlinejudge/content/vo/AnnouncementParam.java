package cn.edu.nciae.onlinejudge.content.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 21:52
 */
@Data
public class AnnouncementParam implements Serializable {
    /**
     * 竞赛id
     */
    private Long competitionId;

    /**
     * 公告id
     */
    private Long announcementId;

    /**
     * 公告标题
     */
    private String announcementName;

    /**
     * 公告内容
     */
    private String announcementContent;

    /**
     * 是否可见
     */
    private Boolean visible;
}

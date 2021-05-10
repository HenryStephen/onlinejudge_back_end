package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.Announcement;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 13:33
 */
public interface AnnouncementServiceApi {

    /**
     * 获取公共公告分页列表
     * @param page
     * @return
     */
    IPage<Announcement> getAnnouncementListPage(Page page);

    /**
     * 获取竞赛公告列表
     * @param competitionId
     * @return
     */
    List<Announcement> list(Long competitionId);

    /**
     * 根据公告id获取公告信息
     * @param announcementId
     * @return
     */
    Announcement getById(Long announcementId);

    /**
     * 添加公告（公共+竞赛）
     * @param announcement
     * @return
     */
    boolean save(Announcement announcement);

    /**
     * 删除公告(公共+竞赛)
     * @param announcementId
     * @return
     */
    boolean removeById(Long announcementId);

    /**
     * 根据id修改公告
     * @param announcement
     * @param announcementId
     */
    boolean update(Announcement announcement, Long announcementId);


}

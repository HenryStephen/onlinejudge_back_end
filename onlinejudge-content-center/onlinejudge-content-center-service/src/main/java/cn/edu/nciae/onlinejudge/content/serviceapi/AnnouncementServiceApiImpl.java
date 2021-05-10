package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.AnnouncementServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Announcement;
import cn.edu.nciae.onlinejudge.content.service.impl.AnnouncementServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 13:34
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class AnnouncementServiceApiImpl extends AnnouncementServiceImpl implements AnnouncementServiceApi {

    /**
     * 获取公共公告分页列表
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Announcement> getAnnouncementListPage(Page page) {
        return super.page(page, new QueryWrapper<Announcement>().eq("competition_id",0L));
    }

    /**
     * 获取竞赛公告列表
     *
     * @param competitionId
     * @return
     */
    @Override
    public List<Announcement> list(Long competitionId) {
        return super.list(new QueryWrapper<Announcement>().eq("competition_id", competitionId));
    }

    /**
     * 根据公告id获取公告信息
     *
     * @param announcementId
     * @return
     */
    @Override
    public Announcement getById(Long announcementId) {
        return super.getById(announcementId);
    }

    /**
     * 添加公告（公共+竞赛）
     * @param announcement
     * @return
     */
    @Override
    public boolean save(Announcement announcement) {
        initAdd(announcement);
        return super.save(announcement);
    }

    /**
     * 删除公告(公共+竞赛)
     * @param announcementId
     * @return
     */
    @Override
    public boolean removeById(Long announcementId) {
        return super.removeById(announcementId);
    }

    /**
     * 根据id修改公告
     *
     * @param announcement
     * @param announcementId
     */
    @Override
    public boolean update(Announcement announcement, Long announcementId) {
        initUpdate(announcement);
        return super.update(announcement, new UpdateWrapper<Announcement>().eq("announcement_id",announcementId));
    }

    /**
     * 初始化Add公告
     * @param announcement
     */
    private void initAdd(Announcement announcement){
        announcement.setCreateTime(new Date());
        announcement.setLastUpdateTime(new Date());
        if (announcement.getCompetitionId() == null){
            announcement.setCompetitionId(0L);
        }
        // 初始化状态
        if (announcement.getIsDeleted() == null) {
            announcement.setIsDeleted(false);
        }
    }

    /**
     * 初始化Update公告
     * @param announcement
     */
    private void initUpdate(Announcement announcement){
        announcement.setLastUpdateTime(new Date());
        if (announcement.getCompetitionId() == null){
            announcement.setCompetitionId(0L);
        }
    }
}

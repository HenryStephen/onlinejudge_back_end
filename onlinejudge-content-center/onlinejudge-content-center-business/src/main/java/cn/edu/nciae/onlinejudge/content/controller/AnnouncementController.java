package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.AnnouncementServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Announcement;
import cn.edu.nciae.onlinejudge.content.vo.AnnouncementListVO;
import cn.edu.nciae.onlinejudge.content.vo.AnnouncementParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 9:52
 */

@RestController
@RequestMapping(value = "/content/announcement")
public class AnnouncementController {

    @Reference(version = "1.0.0",check = false)
    private AnnouncementServiceApi announcementServiceApi;

    /**
     * 获取公共公告列表
     * @return
     */
    @GetMapping
    public ResponseResult<AnnouncementListVO> getAnnouncementList(@RequestParam("pageNum") Integer pageNum,
                                                                  @RequestParam("limit") Integer limit){
        Page page = new Page<Announcement>(pageNum, limit);
        IPage<Announcement> announcements = announcementServiceApi.getAnnouncementListPage(page);
        AnnouncementListVO announcementListVO = AnnouncementListVO.builder()
                                                    .results(announcements.getRecords())
                                                    .total(announcements.getTotal())
                                                    .build();
        return ResponseResult.<AnnouncementListVO>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询公共公告列表成功")
                .data(announcementListVO)
                .build();
    }

    /**
     * 根据公告id获取公告信息
     * @param announcementId
     * @return
     */
    @GetMapping("/{announcementId}")
    public ResponseResult<Announcement> getAnnouncement(@PathVariable("announcementId") Long announcementId){
        Announcement announcement = announcementServiceApi.getById(announcementId);
        return ResponseResult.<Announcement>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询公共公告成功")
                .data(announcement)
                .build();
    }

    /**
     * 添加公告（公共+竞赛）
     * @param announcementParam
     * @return
     */
    @PostMapping
    public ResponseResult<Void> addAnnouncement(@RequestBody AnnouncementParam announcementParam){
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementParam,announcement);
        boolean result = announcementServiceApi.save(announcement);
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("添加公告成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("添加公告失败")
                    .build();
        }
    }

    /**
     * 删除公告(公共+竞赛)
     * @param announcementId
     * @return
     */
    @DeleteMapping("/{announcementId}")
    public ResponseResult<Void> deleteAnnouncement(@PathVariable("announcementId") Long announcementId){
        boolean result = announcementServiceApi.removeById(announcementId);
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("删除公告成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("删除公告失败")
                    .build();
        }
    }

    /**
     * 修改公告(公共+竞赛)
     * @param announcementId
     * @param announcementParam
     * @return
     */
    @PutMapping("/{announcementId}")
    public ResponseResult<Void> updateAnnouncement(@PathVariable("announcementId") Long announcementId,@RequestBody AnnouncementParam announcementParam){
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementParam,announcement);
        boolean result = announcementServiceApi.update(announcement, announcementId);
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("修改公告成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("修改公告失败")
                    .build();
        }
    }
}

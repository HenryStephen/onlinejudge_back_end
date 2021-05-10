package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.AnnouncementServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Announcement;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 21:42
 */
@RestController
@RequestMapping(value = "/content/competition")
public class CompetitionController {

    @Reference(version = "1.0.0",check = false)
    private AnnouncementServiceApi announcementServiceApi;

    /**
     * 获取竞赛公告列表
     * @return
     */
    @GetMapping("/{competitionId}/announcement")
    public ResponseResult<List<Announcement>> getCompetitionAnnouncementList(@PathVariable("competitionId") Long competitionId){
        List<Announcement> announcements = announcementServiceApi.list(competitionId);
        return ResponseResult.<List<Announcement>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询竞赛公告列表成功")
                .data(announcements)
                .build();
    }
}

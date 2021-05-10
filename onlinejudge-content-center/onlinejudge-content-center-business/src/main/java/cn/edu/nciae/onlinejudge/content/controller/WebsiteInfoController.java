package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.vo.WebsiteInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 10:41
 */
@RestController
@RequestMapping("/content/website")
public class WebsiteInfoController {

    @GetMapping
    public ResponseResult<WebsiteInfoVO> getWebsiteInfo(){
        return ResponseResult.<WebsiteInfoVO>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询网站信息成功")
                .data(WebsiteInfoVO.builder()
                        .websiteBaseUrl("www.nciaeoj.com")
                        .websiteName("NCIAE Online Judge")
                        .websiteNameShortcut("NCIAE-OJ")
                        .websiteFooter("Code and Code")
                        .webseiteAuthor("LiaoMu")
                        .allowRegistry(true)
                        .submissionListShowAll(true)
                        .build())
                .build();
    }
}

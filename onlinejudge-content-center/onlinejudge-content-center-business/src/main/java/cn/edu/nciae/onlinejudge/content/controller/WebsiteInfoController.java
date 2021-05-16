package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.WebsiteInfoServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.WebsiteInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 10:41
 */
@RestController
@RequestMapping("/content/website")
public class WebsiteInfoController {

    @Reference(version = "1.0.0",check = false)
    private WebsiteInfoServiceApi websiteInfoServiceApi;

    /**
     * 获取网站基本信息
     * @return
     */
    @GetMapping
    public ResponseResult<WebsiteInfo> getWebsiteInfo(){
        WebsiteInfo websiteInfo = websiteInfoServiceApi.getWebsiteInfo();
        return ResponseResult.<WebsiteInfo>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询网站信息成功")
                .data(websiteInfo)
                .build();
    }

    /**
     * 更改网站信息
     * @return
     */
    @PutMapping
    public ResponseResult<WebsiteInfo> updateWebsiteInfo(@RequestBody WebsiteInfo websiteInfo){
        Boolean result = websiteInfoServiceApi.updateWebsiteInfo(websiteInfo);
        if(result){
            return ResponseResult.<WebsiteInfo>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("更新网站信息成功")
                    .build();
        }else{
            return ResponseResult.<WebsiteInfo>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("更新网站信息失败")
                    .build();
        }
    }
}

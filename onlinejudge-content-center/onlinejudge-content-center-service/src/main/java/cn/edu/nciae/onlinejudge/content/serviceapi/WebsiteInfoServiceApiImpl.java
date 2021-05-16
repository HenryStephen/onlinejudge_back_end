package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.WebsiteInfoServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.WebsiteInfo;
import cn.edu.nciae.onlinejudge.content.service.impl.WebsiteInfoServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 23768
 * @date 2021/5/16
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class WebsiteInfoServiceApiImpl extends WebsiteInfoServiceImpl implements WebsiteInfoServiceApi {

	/**
	 * 获取网站信息
	 *
	 * @return
	 */
	@Override
	public WebsiteInfo getWebsiteInfo() {
		return super.getOne(new QueryWrapper<WebsiteInfo>().eq("website_author","LiaoMu"));
	}

	/**
	 * 更新网站信息
	 * @return
	 */
	@Override
	public Boolean updateWebsiteInfo(WebsiteInfo websiteInfo) {
		return super.update(websiteInfo, new UpdateWrapper<WebsiteInfo>().eq("website_author","LiaoMu"));
	}
}

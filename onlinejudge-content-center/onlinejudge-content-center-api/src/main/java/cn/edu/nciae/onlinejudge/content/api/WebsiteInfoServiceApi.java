package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.WebsiteInfo;

/**
 * @author 23768
 * @date 2021/5/16
 */
public interface WebsiteInfoServiceApi{
	/**
	 * 获取网站信息
	 * @return
	 */
	WebsiteInfo getWebsiteInfo();

	/**
	 * 更新网站信息
	 * @return
	 */
	Boolean updateWebsiteInfo(WebsiteInfo websiteInfo);
}

package cn.edu.nciae.onlinejudge.oauth2.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.commons.utils.MapperUtils;
import cn.edu.nciae.onlinejudge.commons.utils.OkHttpClientUtil;
import cn.edu.nciae.onlinejudge.oauth2.vo.CheckParam;
import cn.edu.nciae.onlinejudge.oauth2.vo.LoginParam;
import cn.edu.nciae.onlinejudge.oauth2.vo.RegisterParam;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserRoleServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.domain.UserRole;
import com.google.common.collect.Maps;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import okhttp3.Response;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 20:34
 */
@RestController
public class LoginController {

    @Value("${business.oauth2.url_oauth_token}")
    public String url_oauth_token;

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource
    public TokenStore tokenStore;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserRoleServiceApi userRoleServiceApi;

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request) throws Exception {
        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();
        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            //账号或密码错误
            return ResponseResult.<Map<String, Object>>builder()
                    .code(BusinessStatus.ADMIN_PASSWORD.getCode())
                    .message(BusinessStatus.ADMIN_PASSWORD.getMessage())
                    .data(null)
                    .build();
        } else if(userDetails != null){
            //userDetails不为空时，判断用户是否被禁用
            Boolean isDisabled = userInfoServiceApi.getIsDisabledByUserName(userDetails.getUsername());
            if(isDisabled){
                return ResponseResult.<Map<String, Object>>builder()
                        .code(BusinessStatus.ADMIN_DISABLED.getCode())
                        .message(BusinessStatus.ADMIN_DISABLED.getMessage())
                        .data(null)
                        .build();
            }
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(url_oauth_token, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.<Map<String, Object>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("登录成功")
                .data(result)
                .build();
    }

    /**
     * 注销
     *
     * @return {@link ResponseResult}
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        // 获取 token 格式为Bearer token
        String bearToken = request.getHeader("Authorization");
//        截取token
        String token = bearToken.substring(7);
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.OK.getCode())
                .message("用户已注销")
                .build();
    }

    /**
     * 检查用户名或邮箱是否重复
     * @param checkParam
     * @return
     */
    @PostMapping("/user/checkUsernameOrEmail")
    public ResponseResult<Boolean> checkUsernameOrEmail(@RequestBody CheckParam checkParam){
        if(checkParam.getUsername() != null){
            Boolean result = userInfoServiceApi.checkUserName(checkParam.getUsername());
            return ResponseResult.<Boolean>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("检测成功")
                    .data(result)
                    .build();
        }
        if(checkParam.getEmail() != null){
            Boolean result = userInfoServiceApi.checkEmail(checkParam.getEmail());
            return ResponseResult.<Boolean>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("检测成功")
                    .data(result)
                    .build();
        }
        return ResponseResult.<Boolean>builder()
                .code(BusinessStatus.OK.getCode())
                .message("检测成功")
                .data(false)
                .build();
    }

    /**
     * 注册
     * @param registerParam
     * @return
     */
    @PostMapping("/user/register")
    public ResponseResult<Void> register(@RequestBody RegisterParam registerParam){
        System.out.println(registerParam.toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(registerParam.getUsername());
        userInfo.setUserPassword(passwordEncoder.encode(registerParam.getPassword()));
        userInfo.setUserEmail(registerParam.getEmail());
        userInfo = userInfoServiceApi.saveUserInfo(userInfo);
        System.out.println(userInfo.getUserId());
        if(userInfo.getUserId() != null){
            UserRole userRole = new UserRole();
            userRole.setId(0L);
            userRole.setUserId(userInfo.getUserId());
            userRole.setRoleId(3L);
            boolean result = userRoleServiceApi.save(userRole);
            if(result){
                return ResponseResult.<Void>builder()
                        .message("注册成功")
                        .code(BusinessStatus.OK.getCode())
                        .data(null)
                        .build();
            }
        }
        return ResponseResult.<Void>builder()
                .message("注册失败")
                .code(BusinessStatus.FAIL.getCode())
                .data(null)
                .build();
    }
}

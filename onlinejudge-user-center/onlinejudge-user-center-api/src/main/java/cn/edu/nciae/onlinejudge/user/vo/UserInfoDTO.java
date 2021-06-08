package cn.edu.nciae.onlinejudge.user.vo;

import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author liaomu
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "userInfo", resultMap = "UserInfoViewResultMap")
public class UserInfoDTO extends UserInfo {

    /**
     * 角色类型
     */
    private String roleType;
}

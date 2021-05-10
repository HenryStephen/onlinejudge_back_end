package cn.edu.nciae.onlinejudge.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码参数
 * <p>
 * Description:
 * </p>
 *
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/18 22:26
 */

@Data
public class PasswordParam implements Serializable {

    private String oldPassword;
    private String newPassword;
}

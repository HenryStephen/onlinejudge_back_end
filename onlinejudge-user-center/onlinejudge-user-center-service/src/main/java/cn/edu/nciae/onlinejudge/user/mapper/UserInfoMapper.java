package cn.edu.nciae.onlinejudge.user.mapper;

import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Entity cn.edu.nciae.onlinejudge.user.domain.UserInfo
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 查看用户分页列表
     * @param pageP
     * @param keyword
     * @return
     */
    IPage<UserInfoDTO> selectUserInfoVOListPage(Page pageP, String keyword);

    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return
     */
    UserInfoDTO selectUserDtoByUserId(Long userId);

    /**
     * 获取账户是否已经被禁用
     * @param username
     * @return
     */
    Boolean selectIsDisabled(String username);

    /**
     * 根据ac个数降序排序
     * @param page
     * @return
     */
    IPage<UserInfoDTO> selectUserInfoListPageByACnumberDESC(Page page);
}





package cn.edu.nciae.onlinejudge.user.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoListVo {

    /**
     * results set
     */
    private List<UserInfoDTO> results;

    /**
     * Total Problem Num
     */
    private Long total;
}

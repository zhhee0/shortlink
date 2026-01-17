package com.heng.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class UserActualRespDTO {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}

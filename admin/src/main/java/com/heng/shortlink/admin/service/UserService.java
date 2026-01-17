package com.heng.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.shortlink.admin.dao.entity.UserDO;
import com.heng.shortlink.admin.dto.req.UserLoginReqDTO;
import com.heng.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.heng.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.heng.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {
    UserRespDTO getUserByUsername(String name);

    Boolean hasUsername(String username);

    void register(UserRegisterReqDTO requestParam);

    UserLoginRespDTO login(UserLoginReqDTO dto);

    Boolean checkLogin(String username, String token);
}

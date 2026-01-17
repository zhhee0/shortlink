package com.heng.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.heng.shortlink.admin.common.convention.result.Result;
import com.heng.shortlink.admin.common.convention.result.Results;
import com.heng.shortlink.admin.dto.resp.UserActualRespDTO;
import com.heng.shortlink.admin.dto.resp.UserRespDTO;
import com.heng.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 根据用户名
     *
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }
    @GetMapping("/api/shortlink/v1/actual/user/{username}")
    public Result<UserActualRespDTO> geActualUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(BeanUtil.toBean(result, UserActualRespDTO.class));
    }
}

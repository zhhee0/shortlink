package com.heng.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.heng.shortlink.admin.common.convention.result.Result;
import com.heng.shortlink.admin.common.convention.result.Results;
import com.heng.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.heng.shortlink.admin.dto.resp.UserActualRespDTO;
import com.heng.shortlink.admin.dto.resp.UserRespDTO;
import com.heng.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 根据用户名字
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

    @GetMapping("/api/shortlink/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(userService.hasUsername(username));
    }
@PostMapping("/api/shortlink/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO){
        userService.register(userRegisterReqDTO);
        return Results.success();
    }
}

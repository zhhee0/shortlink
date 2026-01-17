package com.heng.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heng.shortlink.admin.common.convention.result.Result;
import com.heng.shortlink.admin.common.convention.result.Results;
import com.heng.shortlink.admin.dao.entity.UserDO;
import com.heng.shortlink.admin.dto.req.UserLoginReqDTO;
import com.heng.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.heng.shortlink.admin.dto.req.UserUpdateDTO;
import com.heng.shortlink.admin.dto.resp.UserActualRespDTO;
import com.heng.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.heng.shortlink.admin.dto.resp.UserRespDTO;
import com.heng.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.heng.shortlink.admin.common.enums.UserErrorCodeEnum.USER_EXIST;
import static com.heng.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_NULL;

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

    @GetMapping("/api/shortlink/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    @PostMapping("/api/shortlink/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
    @PutMapping("/api/shortlink/v1/user")
    public Result<Void> update(@RequestBody UserUpdateDTO dto){
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            return Results.failure(USER_NAME_NULL);
        }
        LambdaUpdateWrapper<UserDO> wrapper = Wrappers.lambdaUpdate(UserDO.class).eq(UserDO::getUsername, dto.getUsername());
        boolean ok = userService.update(BeanUtil.toBean(dto, UserDO.class), wrapper);
        if (!ok) return Results.failure(USER_EXIST);
        return Results.success();
    }

    @PostMapping("/api/shortlink/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO dto){
        UserLoginRespDTO result = userService.login(dto);
        return Results.success(result);
    }

    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

}

package com.heng.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.shortlink.admin.common.convention.exception.ClientException;
import com.heng.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.heng.shortlink.admin.dao.entity.UserDO;
import com.heng.shortlink.admin.dao.mapper.UserMapper;
import com.heng.shortlink.admin.dto.resp.UserRespDTO;
import com.heng.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements UserService {


    @Override
    public UserRespDTO getUserByUsername(String name) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, name);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO userRespDTO = new UserRespDTO();
        BeanUtils.copyProperties(userDO,userRespDTO);
        return userRespDTO;
    }
}

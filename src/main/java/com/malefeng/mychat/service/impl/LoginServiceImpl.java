package com.malefeng.mychat.service.impl;

import com.malefeng.mychat.bean.dto.request.LoginRequest;
import com.malefeng.mychat.bean.dto.response.Response;
import com.malefeng.mychat.bean.entity.UserEntity;
import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.dao.mapper.UserDao;
import com.malefeng.mychat.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public Response login(LoginRequest request) {
        //check into
        List<UserEntity> userEntityList = userDao.select(new UserEntity().setUserCode(request.getUserName()));
        if (userEntityList.isEmpty()){
            return new Response().error(RuntimeErrorEnum.BAD_REQUEST.getCode(),"account not exist");
        }
        //login
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUserName(),request.getPassWord());
        subject.login(token);
        UserEntity currentUser = (UserEntity)subject.getPrincipal();
        return new Response(currentUser);
    }

    @Override
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Response(RuntimeErrorEnum.SUCCESS);
    }
}

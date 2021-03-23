package com.malefeng.mychat.service.impl;

import com.malefeng.mychat.bean.dto.request.UserModifyPswdRequest;
import com.malefeng.mychat.bean.dto.response.Response;
import com.malefeng.mychat.bean.entity.UserEntity;
import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.dao.mapper.UserDao;
import com.malefeng.mychat.service.UserService;
import com.malefeng.mychat.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public Response modifyPswd(UserModifyPswdRequest request) throws Exception {
        UserEntity oldUser = dao.selectOne(new UserEntity().setId(request.getId()).setPassWord(request.getOldPassWord()));
        if (null == oldUser){
            return new Response().error(RuntimeErrorEnum.BAD_REQUEST.getCode(),"历史密码错误");
        }
        oldUser.setPassWord(StringUtil.MD5(request.getNewPassWord()));
        dao.updateByPrimaryKey(oldUser);
        return new Response();
    }
}

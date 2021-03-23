package com.malefeng.mychat.service;

import com.malefeng.mychat.bean.dto.request.UserModifyPswdRequest;
import com.malefeng.mychat.bean.dto.response.Response;

public interface UserService {


    Response modifyPswd(UserModifyPswdRequest request) throws Exception;
}

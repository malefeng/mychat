package com.malefeng.mychat.service;

import com.malefeng.mychat.bean.dto.request.LoginRequest;
import com.malefeng.mychat.bean.dto.response.Response;

public interface LoginService {

    /**
     * description: login <br>
     * version: 1.0 <br>
     * date: 2021/3/16 18:45 <br>
     * author: mlf <br>
     * 
     * @param request
     * @return com.malefeng.mychat.bean.dto.response.Response
     */ 
    Response login(LoginRequest request);

    /**
     * description: logout <br>
     * version: 1.0 <br>
     * date: 2021/3/16 18:45 <br>
     * author: mlf <br>
     * 
     * @param 
     * @return com.malefeng.mychat.bean.dto.response.Response
     */ 
    Response logout();
}

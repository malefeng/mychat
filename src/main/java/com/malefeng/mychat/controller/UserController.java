package com.malefeng.mychat.controller;

import com.malefeng.mychat.bean.dto.request.UserModifyPswdRequest;
import com.malefeng.mychat.bean.dto.response.Response;
import com.malefeng.mychat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = { "账户信息维护" })
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/modifyPasd")
    @ApiImplicitParam(name = "UserModifyPswdRequest", value = "修改密码请求包")
    @ApiOperation(value = "修改密码")
    public Response modifyPasd(@RequestBody @Valid UserModifyPswdRequest request) throws Exception {
        return service.modifyPswd(request);
    }
}

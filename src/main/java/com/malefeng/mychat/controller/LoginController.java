package com.malefeng.mychat.controller;

import com.malefeng.mychat.bean.dto.request.LoginRequest;
import com.malefeng.mychat.bean.dto.response.Response;
import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@Api(tags = { "登录" })
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    @ApiImplicitParam(name = "LoginRequest", value = "用户登录请求包")
    @ApiOperation(value = "登录请求")
    public Response login(@RequestBody @Valid LoginRequest request){
        return service.login(request);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销请求")
    public Response logout(){
        return service.logout();
    }

    @GetMapping("/loginFailure")
    @ApiIgnore
    public Response loginFailure(ModelMap map){
        return new Response(RuntimeErrorEnum.UN_AUTH);
    }

    @GetMapping("/unAuth")
    @ApiIgnore
    public Response unAuth(ModelMap map){
        return new Response(RuntimeErrorEnum.UN_AUTH);
    }
}

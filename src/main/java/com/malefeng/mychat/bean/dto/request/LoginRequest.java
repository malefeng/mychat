package com.malefeng.mychat.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(description = "用户登录请求包")
public class LoginRequest implements Serializable {

    @NotBlank(message = "用户账号不能为空")
    @ApiModelProperty(value= "账号", required = true)
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    @ApiModelProperty(value= "密码", required = true)
    private String passWord;
}

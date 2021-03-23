package com.malefeng.mychat.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "修改密码请求包")
public class UserModifyPswdRequest implements Serializable {

    @ApiModelProperty(value= "用户id", required = true)
    @NotNull(message = "账户id不能为空")
    private Integer id;

    @ApiModelProperty(value= "旧密码", required = true)
    @NotNull(message = "历史密码不能为空")
    private String oldPassWord;

    @ApiModelProperty(value= "新密码", required = true)
    @NotNull(message = "新密码不能为空")
    private String newPassWord;
}

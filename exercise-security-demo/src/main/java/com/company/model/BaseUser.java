package com.company.model;

import com.company.validator.MyConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class BaseUser {
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;


    private String salt;

    private String sex;

    private String cellPhone;

    @Email
    private String email;

    private Byte enable;

    @MyConstraint(message = "这是一个测试注解")
    private String remark;

    private Date createTime;

    private String createBy;

    private Date lastUpdateTime;

    private String lastUpdateBy;

}

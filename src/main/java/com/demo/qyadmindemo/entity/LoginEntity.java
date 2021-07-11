package com.demo.qyadmindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 登录用
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginEntity {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 验证码图片 UUID
     */
    @NotBlank(message = "验证码 UUID 不能为空")
    private String uuid;
}

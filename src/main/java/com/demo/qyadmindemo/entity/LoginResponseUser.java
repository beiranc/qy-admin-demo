package com.demo.qyadmindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录成功后返回的用户信息
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseUser {
    private Long userId;

    private String username;

    private String nname;
}

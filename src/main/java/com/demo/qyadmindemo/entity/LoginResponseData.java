package com.demo.qyadmindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * 登录成功后的信息
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseData {
    private String token;

    private String exipreTime;

    private LoginResponseUser user;

    private Set<String> roles;

    private Set<String> permissions;
}

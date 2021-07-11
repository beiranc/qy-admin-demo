package com.demo.qyadmindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录成功后返回的对象
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseEntity {
    private LoginResponseData data;
}
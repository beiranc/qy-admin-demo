package com.demo.qyadmindemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * 修改用户过期时间
 */
@Data
public class UserUpdateVo {
    /**
     * JWT token
     */
    private String token;

    /**
     * 用户 ID
     */
    @PositiveOrZero(message = "用户 ID 不能为空")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 所属企业 ID
     */
    @PositiveOrZero(message = "所属企业 ID 不能为空")
    private Integer companyId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    /**
     * 用户状态, 0 锁定, 1 有效
     */
    @NotBlank(message = "用户状态不能为空")
    private String status;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 上次登录时间
     */
    @NotNull(message = "上一次登录时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 用户过期时间
     */
    @FutureOrPresent(message = "过期时间只能选择未来的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
     * 性别, 0 男, 1 女, 2 保密
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 用户描述
     */
    @NotBlank(message = "用户描述不能为空")
    private String description;

    /**
     * 是否购买, 0 未支付, 1 支付
     */
    @NotBlank(message = "请选择该用户是否付费")
    private String pay;

    @NotBlank(message = "角色 ID 不能为空")
    private String roleId;
}

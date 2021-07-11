package com.demo.qyadmindemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 创建优惠券时所需字段
 */
@Data
public class TicketCreateVo {
    /**
     * JWT token
     */
    @NotBlank(message = "请传入 Token")
    private String token;

    /**
     * 优惠券金额
     */
    @PositiveOrZero(message = "优惠券金额不能为空")
    private BigDecimal amount;

    /**
     * 创建人(nname)
     */
    @NotBlank(message = "请传入创建人")
    private String createUser;

    /**
     * 创建人联系方式
     */
    @NotBlank(message = "请传入创建人联系方式")
    private String createMobile;

    /**
     * 有效期, yyyy-MM-dd HH:mm:ss
     */
    @FutureOrPresent(message = "请选择一个合法有效期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}

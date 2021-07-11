package com.demo.qyadmindemo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查询优惠券时返回
 */
@Data
public class TicketResponseEntity {

    private String id;

    /**
     * 优惠券金额
     */
    private BigDecimal amount;

    /**
     * 优惠券状态, 1 有效, 0 已使用
     */
    private String status;

    /**
     * 有效期
     */
    private String endTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建人联系方式
     */
    private String createMobile;
}

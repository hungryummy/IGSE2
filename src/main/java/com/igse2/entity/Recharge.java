package com.igse2.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ：ZhRunXin
 * @date ：Created in 2023/1/15 23:14
 * @email ：zhrunxin33@gmail.com
 * @description：
 */
@Data
public class Recharge {

    @NotNull
    private String customerId;

    @NotNull
    private String voucher;
}

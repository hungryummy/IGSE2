package com.igse2.service;

import com.igse2.entity.Customer;
import com.igse2.entity.Voucher;

import java.util.List;

public interface VoucherService {

    public List<Voucher> findAll();

    Voucher queryUsedExist(String voucher);
}

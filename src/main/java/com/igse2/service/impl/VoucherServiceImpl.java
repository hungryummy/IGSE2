package com.igse2.service.impl;


import com.igse2.entity.Voucher;
import com.igse2.mapper.VoucherMapper;
import com.igse2.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<Voucher> findAll() {
        List<Voucher> vouchers = voucherMapper.selectAll();
        return vouchers;
    }

    @Override
    public Voucher queryUsedExist(String voucher) {
        Voucher voucher1 = new Voucher();
        voucher1.setEVCCode(voucher);
        return voucherMapper.selectOne(voucher1);
    }
}

package com.igse2.mapper;

import com.igse2.entity.Voucher;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

@Repository
public interface VoucherMapper extends Mapper<Voucher> {
}

package com.igse2.mapper;

import com.igse2.entity.Voucher;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

@Repository
public interface VoucherMapper extends Mapper<Voucher> {
    @Select("select EVC_code as EVCCode,used from Voucher where EVC_code = #{voucher}")
    Voucher selectOneVoucher(String voucher);

    @Update("UPDATE Voucher  SET used = #{used} WHERE  EVC_code = #{EVCCode}")
    void updateByPrimaryKeyDiy(Voucher voucher1);
}

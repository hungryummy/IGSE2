package com.igse2.mapper;

import com.igse2.entity.Customer;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CustomerMapper extends Mapper<Customer> {
}

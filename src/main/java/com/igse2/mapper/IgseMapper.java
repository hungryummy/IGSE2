package com.igse2.mapper;

import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface IgseMapper extends Mapper<Igse> {

}

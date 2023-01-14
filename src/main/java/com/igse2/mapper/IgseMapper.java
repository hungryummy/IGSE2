package com.igse2.mapper;

import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import com.igse2.entity.Reading;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IgseMapper extends Mapper<Igse> {

    @Select("select customer_id as customerId, submission_date as submissionDate,elet_reading_night as eletReadingNight,elec_readings_day as elecReadingsDay,gas_reading as gasReading from (select *,row_number() over(PARTITION by customer_id order by submission_date desc) as rowid\n" +
            "                  from (select c.customer_id,c.property_type,c.bedroom_num,R.submission_date,R.elec_readings_day,R.elet_reading_night,R.gas_reading from Customer c\n" +
            "    right join Reading R on c.customer_id = R.customer_id where property_type = #{propertyType} and bedroom_num = #{bedroomNum}) tem" +
            "              ) a where rowid<=2")
    List<Reading> findTop2Record(@Param("propertyType") String propertyType, @Param("bedroomNum") String bedroomNum);
}

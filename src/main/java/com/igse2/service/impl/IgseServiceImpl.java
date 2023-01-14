package com.igse2.service.impl;


import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import com.igse2.entity.Reading;
import com.igse2.mapper.IgseMapper;
import com.igse2.service.IgseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IgseServiceImpl implements IgseService {

    @Autowired IgseMapper igseMapper;

    @Override
    public List<Igse> findAll() {
        List<Igse> igse = igseMapper.selectAll();
        return igse;
    }

    @Override
    public List<Igse> findByProperty(String Property) {
        return null;
    }

    @Override
    public Double costPerDay(String propertyType, String bedroomNum) {
        Integer dayValue = 0;
        Integer nightValue = 0;
        Integer gasValue = 0;
        Integer day = 0;
        List<Reading> readings = igseMapper.findTop2Record(propertyType,bedroomNum);
        // 分用户处理
        Map<String, List<Reading>> collect = readings.stream().collect(Collectors.groupingBy(Reading::getCustomerId));
        // 循环处理同用户下的数据
        for (String s : collect.keySet()) {
            List<Reading> readings1 = collect.get(s);
            if (readings1.size() !=2){
                throw new RuntimeException("抄表数据必须超过两条");
            }
            dayValue = dayValue + Math.abs(readings1.get(0).getElecReadingsDay() - readings1.get(1).getElecReadingsDay());
            nightValue = nightValue + Math.abs(readings1.get(0).getEletReadingNight() - readings1.get(1).getEletReadingNight());
            gasValue = gasValue + Math.abs(readings1.get(0).getGasReading() - readings1.get(1).getGasReading());
            try {
                day = Math.toIntExact(day + getInterval(readings1.get(0).getSubmissionDate(), readings1.get(1).getSubmissionDate()));
            }catch (Exception e){
                throw new RuntimeException(e);
            }

        }
        //Example: Bill = (200 -100) x 0.34 + (500-250) x 0.2 + (1600-800) x 0.1 + 0.74 x 30 days = £186.2 2
        double result = (dayValue * 0.34 + nightValue * 0.2 + 0.74 * day) / day;
        return result;
    }

    //获取两个传入时间相隔的天数
    public static long getInterval(Date begin_date, Date end_date) throws ParseException {
        long day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(begin_date != null){
            String begin = sdf.format(begin_date);
            begin_date  = sdf.parse(begin);
        }else {
            begin_date=sdf.parse(sdf.format(new Date()));
        }
        if(end_date!= null){
            String end= sdf.format(end_date);
            end_date= sdf.parse(end);
        }else{
            end_date=sdf.parse(sdf.format(new Date()));
        }
        day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
        return day>=0?day:-day;
    }

}

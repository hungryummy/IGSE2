package com.igse2.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.igse2.entity.Customer;
import com.igse2.entity.Reading;
import com.igse2.mapper.CustomerMapper;
import com.igse2.mapper.ReadingMapper;
import com.igse2.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingMapper readingMapper;

    @Override
    public List<Reading> viewAll() {
        List<Reading> reading = readingMapper.selectAll();
        return reading;
    }

    @Override
    public Page<Reading> search(Map searchMap) {
        Example example = new Example(Reading.class);
        int pageNum = 1;
        int pageSize = 50;
        String pageNum1 = String.valueOf(pageNum);
        String pageSize1 = String.valueOf(pageSize);
        if (searchMap != null){
            Example.Criteria criteria = example.createCriteria();
            if(StringUtil.isNotEmpty((String)searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("submissionDate",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("submissionDate",searchMap.get("endTime"));
            }
            if (StringUtil.isNotEmpty((String)searchMap.get("customerId"))){
                criteria.andLike("customerId","%"+(String) searchMap.get("customerId")+"%");
            }
            if (StringUtil.isNotEmpty((String)searchMap.get(pageNum1))){
                pageNum =Integer.parseInt((String) searchMap.get(pageNum1));
            }
            if (StringUtil.isNotEmpty((String)searchMap.get(pageSize1))){
                pageSize =Integer.parseInt((String) searchMap.get(pageSize1));
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        Page <Reading> readings = (Page<Reading>) readingMapper.selectByExample(example);
        return readings;
    }

    @Override
    public Boolean add(Reading reading) {
        int row =  readingMapper.insert(reading);
        if (row > 0 ){

            return true;
        }else{
            return false;
        }
    }

    @Override
    public Reading findById(Integer readingId) {
        return readingMapper.selectByPrimaryKey(readingId);
    }

    @Override
    public Boolean update(Reading reading) {
        int row =  readingMapper.updateByPrimaryKey(reading);
        if (row > 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean updateStatus(String status, Integer readingId,String customerId) {
        Reading reading = new Reading();
        reading.setReadingId(readingId);
        reading.setStatus(status);
        reading.setCustomerId(customerId);
        int row = readingMapper.updateByPrimaryKey(reading);
        if (row > 0){
            return true;
        }else{
            return false;
        }
    }


}

package com.igse2.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.igse2.entity.Rate;
import com.igse2.entity.Reading;
import com.igse2.mapper.RateMapper;
import com.igse2.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;


@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateMapper rateMapper;

    @Override
    public List<Rate> viewAll() {
        List<Rate> rate = rateMapper.selectAll();
        return rate;
    }

    @Override
    public Page<Rate> search(Map searchMap) {
        Example example = new Example(Rate.class);
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
        Page <Rate> rate = (Page<Rate>) rateMapper.selectByExample(example);
        return rate;
    }
}

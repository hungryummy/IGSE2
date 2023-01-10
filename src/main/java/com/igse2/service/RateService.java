package com.igse2.service;

import com.github.pagehelper.Page;
import com.igse2.entity.Rate;
import com.igse2.entity.Reading;


import java.util.List;
import java.util.Map;

public interface RateService {

    public List<Rate>  viewAll();

    public Page<Rate> search(Map searchMap);
}

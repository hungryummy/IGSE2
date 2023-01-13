package com.igse2.service.impl;


import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import com.igse2.mapper.IgseMapper;
import com.igse2.service.IgseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

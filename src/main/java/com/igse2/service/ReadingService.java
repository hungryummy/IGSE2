package com.igse2.service;

import com.github.pagehelper.Page;
import com.igse2.entity.Customer;
import com.igse2.entity.Reading;

import java.util.List;
import java.util.Map;

public interface ReadingService {


    public List<Reading> viewAll();


    public Page<Reading> search(Map searchMap);


    public Boolean add(Reading reading);


    public Reading findById(Integer readingId);

    Boolean update(Reading reading);

    Boolean updateStatus(String status, Integer readingId, String customerId);
}

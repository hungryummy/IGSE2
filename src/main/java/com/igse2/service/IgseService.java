package com.igse2.service;

import com.igse2.entity.Customer;
import com.igse2.entity.Igse;

import java.util.List;
import java.util.Map;

public interface IgseService {

    public List<Igse> findAll();


    List<Igse> findByProperty(String Property);


}

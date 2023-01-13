package com.igse2.entity;


import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Customer")
public class Igse  implements Serializable {

    private String propertyType;

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

}

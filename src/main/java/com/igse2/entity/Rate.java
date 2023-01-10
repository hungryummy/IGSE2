package com.igse2.entity;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name= "Taiff")
public class Rate implements Serializable {
    @Id
    private String taiffType;
    private String rate;

    public String getTaiffType() {
        return taiffType;
    }

    public void setTaiffType(String taiffType) {
        this.taiffType = taiffType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

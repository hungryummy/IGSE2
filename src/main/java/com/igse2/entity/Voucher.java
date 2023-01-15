package com.igse2.entity;


import javax.persistence.Table;
import java.io.Serializable;

@Table(name= "Voucher")
public class Voucher  implements Serializable {


    private String EVCCode;
    private int used;

    public String getEVCCode() {
        return EVCCode;
    }

    public void setEVCCode(String EVCCode) {
        this.EVCCode = EVCCode;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}

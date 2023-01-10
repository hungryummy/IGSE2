package com.igse2.entity;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Reading")
public class Reading implements Serializable {

    @Id
    private Integer readingId;
    private String customerId;
    private Date submissionDate;
    private Integer elecReadingsDay;
    private Integer eletReadingNight;
    private Integer gasReading;
    private String status;


    public Integer getReadingId() {
        return readingId;
    }

    public void setReadingId(Integer readingId) {
        this.readingId = readingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Integer getElecReadingsDay() {
        return elecReadingsDay;
    }

    public void setElecReadingsDay(Integer elecReadingsDay) {
        this.elecReadingsDay = elecReadingsDay;
    }

    public Integer getEletReadingNight() {
        return eletReadingNight;
    }

    public void setEletReadingNight(Integer eletReadingNight) {
        this.eletReadingNight = eletReadingNight;
    }

    public Integer getGasReading() {
        return gasReading;
    }

    public void setGasReading(Integer gasReading) {
        this.gasReading = gasReading;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

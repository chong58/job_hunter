package com.job.model;

import java.util.Date;

public class Job {

    private int userId;
    private int id;
    private String companyName;
    private String position;
    private Date applicationDay;
    private Date applicationCloseDay;
    private String status;
    private String jobLink;

    public int getUserId(){return userId;}

    public void setUserId(int userId){this.userId = userId;}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCompanyName(){ return companyName;}

    public void setCompanyName(String companyName){ this.companyName = companyName;}

    public String getPosition() { return position;}

    public void setPosition(String position) {this.position = position;}

    public Date getApplicationDay(){ return applicationDay;}

    public void setApplicationDay(Date applicationDay) {this.applicationDay = applicationDay;}

    public Date getApplicationCloseDay(){ return applicationCloseDay;}
    public void setApplicationCloseDay(Date applicationCloseDay) {this.applicationCloseDay = applicationCloseDay;}

    public String getStatus() {return status;}
    public void setStatus(String status){this.status = status;}

    public String getJobLink(){return jobLink;}

    public void setJobLink(String jobLink){ this.jobLink = jobLink;}
}

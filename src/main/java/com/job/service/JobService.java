package com.job.service;

import com.job.model.Job;

import java.util.Date;
import java.util.List;

public interface JobService {
    int getTotal();
    void addJob(Job job);
    void deleteJob(int id);
    void updateJob(Job job);
    Job getJob(int id);
    List<Job> getJobByName(String companyName);
    List<Job> list(int userId, int start, int count);
    List<Job> getJobByTime(int userId, Date startDay);
}

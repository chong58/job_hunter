package com.job.dao;

import com.job.model.Job;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JobDao {
    int getTotal();
    void addJob(Job job);
    void deleteJob(int id);
    void updateJob(Job job);
    Job getJob(int id);
    List<Job> getJobByName(String companyName);
    List<Job> list(int userId, int start, int count);
    List<Job> getJobByTime(int userId, Date startDay);
}

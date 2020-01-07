package com.job.service.impl;

import com.job.dao.JobDao;
import com.job.model.Job;
import com.job.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    public int getTotal(){
        return jobDao.getTotal();
    }

    public void addJob(Job job){
        jobDao.addJob(job);
    }

    public void deleteJob(int id){
        jobDao.deleteJob(id);
    }

    public void updateJob(Job job){
        jobDao.updateJob(job);
    }

    public Job getJob(int id){
        return jobDao.getJob(id);
    }

    public List<Job> getJobByName(String companyName) { return jobDao.getJobByName(companyName);}

    public List<Job> list(int userId, int start,int count){
        return jobDao.list(userId, start,count);
    }

    public List<Job> getJobByTime(int userId, Date startDay){
        return jobDao.getJobByTime(userId,startDay);
    }
}

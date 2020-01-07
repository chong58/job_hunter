package com.job.controller;

import com.job.model.Job;
import com.job.service.JobService;
import com.job.service.UserService;
import com.job.util.Page;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    UserService userService;

    public JobController jobController;

    @PostConstruct
    public void init(){
        jobController = this;
        jobController.userService = this.userService;
    }

    @RequestMapping("/addJob")
    public String addJob(HttpServletRequest request, HttpServletResponse response){
        Job job = new Job();
        if(request.getParameter("userId") == null) return null;
        int userId = Integer.valueOf(request.getParameter("userId"));
        String companyName = request.getParameter("companyName");
        String position = request.getParameter("position");
        Date applicationDay = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            applicationDay = simpleDateFormat.parse(request.getParameter("applicationDay"));
            System.out.println(applicationDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date applicationCloseDay = null;
        try {
            applicationCloseDay = simpleDateFormat.parse(request.getParameter("applicationCloseDay"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String status = request.getParameter("status");
        String jobLink = request.getParameter("jobLink");

        job.setUserId(userId);
        job.setCompanyName(companyName);
        job.setPosition(position);
        job.setApplicationDay(applicationDay);
        job.setApplicationCloseDay(applicationCloseDay);
        job.setStatus(status);
        job.setJobLink(jobLink);
        jobService.addJob(job);
        return "redirect:listJob";
    }

    @RequestMapping("/listJob")
    public String listJob(HttpServletRequest request, HttpServletResponse response){
        int start = 0;
        int count  =5;
        try {
            start = Integer.parseInt(request.getParameter("page.start"));
            count = Integer.parseInt(request.getParameter("page.count"));
        } catch (Exception e) {
        }

        Page page = new Page(start,count);
        //if(request.getParameter("userId") == null){return "999999";}
        //int userId = Integer.valueOf(request.getParameter("userId"));
        int userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());

        List<Job> jobs = jobService.list(userId,page.getStart(),page.getCount());
        int total = jobs.size();
        page.setTotal(total);

        request.setAttribute("jobs", jobs);
        request.setAttribute("page", page);

        return "listJob";

    }

    @RequestMapping("/deleteJob")
    public String deleteJob(int id){
        jobService.deleteJob(id);
        return "redirect:listJob";
    }

    @RequestMapping("/editJob")
    public ModelAndView editJob(int id){
        ModelAndView mav = new ModelAndView("editJob");
        Job job = jobService.getJob(id);
        mav.addObject("job", job);
        return mav;
    }

    @RequestMapping("/updateJob")
    public String updateJob(HttpServletRequest request, HttpServletResponse response){
        Job job = new Job();
        int id = Integer.parseInt(request.getParameter("id"));
        String companyName = request.getParameter("companyName");
        String position = request.getParameter("position");
        Date applicationDay = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            applicationDay = simpleDateFormat.parse(request.getParameter("applicationDay"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date applicationCloseDay = null;
        try {
            applicationCloseDay = simpleDateFormat.parse(request.getParameter("applicationCloseDay"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String status = request.getParameter("status");
        String jobLink = request.getParameter("jobLink");
        job.setCompanyName(companyName);
        job.setPosition(position);
        job.setApplicationDay(applicationDay);
        job.setApplicationCloseDay(applicationCloseDay);
        job.setStatus(status);
        job.setJobLink(jobLink);
        jobService.updateJob(job);

        return "redirect:listJob";
    }

    @RequestMapping("/searchJob")
    public String searchJob(HttpServletRequest request, HttpServletResponse response){
        Page page = new Page(0,3);
        String companyName = request.getParameter("companyName");
        List<Job> jobs = jobService.getJobByName(companyName);
        page.setTotal(jobs.size());

        request.setAttribute("jobs", jobs);
        request.setAttribute("page", page);
        System.out.println(jobs.get(0).getCompanyName() + 999);
        return "listJob";
    }

    @RequestMapping("/searchJobNumber")

    public String searchJobNumber(HttpServletRequest request, HttpServletResponse response){
        return "showGraph";
    }

    @RequestMapping("/calculateEverydayJobNumber")
    public String calculateEJN(HttpServletRequest request, HttpServletResponse response){
        int userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDay = null;
        try{
            startDay = simpleDateFormat.parse(request.getParameter("startDay"));
            System.out.println(startDay);
        }catch(ParseException e ){
            e.printStackTrace();
        }
        Date endDay = null;
        try{
            endDay = simpleDateFormat.parse(request.getParameter("endDay"));
        }catch(ParseException e){
            e.printStackTrace();
        }
        Map<Date,Integer> records = new HashMap<Date,Integer>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDay);
        while(startDay.before(endDay)){
            List<Job> list = new ArrayList<>();
            list = jobService.getJobByTime(userId,startDay);
            records.put(startDay,list.size());
            System.out.println(startDay);
            System.out.println(list.size());
            calendar.add(Calendar.DATE, 1);
            startDay = calendar.getTime();
        }

        CategoryDataset dataset = getDataSet(records);
        JFreeChart freeChart = createChart(dataset);
        String filename = null;
        try{
            filename = ServletUtilities.saveChartAsPNG(freeChart, 600, 400, null);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;

        String image="<img src='"+ graphURL+ "' width=600 height=400 border=0 usemap='#"+filename + "'/>";

        request.setAttribute("image", image);
        return "showGraph";
    }


    public static DefaultCategoryDataset getDataSet(Map<Date,Integer> records){
        //创建数据集
        DefaultCategoryDataset dataSet=new DefaultCategoryDataset();
        //添加数据
        int i = 0;
        for(Date key: records.keySet()){
            dataSet.addValue(records.get(key),"date",i+"");
            i++;
        }

        return dataSet;

    }

    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart("job records", // 标题
                "date", // categoryAxisLabel （category轴，横轴，X轴标签）
                "number", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        return jfreechart;
    }


    @RequestMapping("/startEmailService")
    public String startEmailService(HttpServletRequest request, HttpServletResponse response){
        int userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        System.out.println(userId);
        if(jobController.userService == null) {
            System.out.println("000000");
        }
        try {
            userService.startEmailService(userId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            ;
        return "redirect:listJob";
    }

    @RequestMapping("/stopEmailService")
    public String stopEmailService(HttpServletRequest request, HttpServletResponse response){
        int userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        userService.stopEmailService(userId);
        return "redirect:listJob";
    }



}

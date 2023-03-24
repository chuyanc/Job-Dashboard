package com.levralabs.Jobboard.controller;

import com.levralabs.Jobboard.entity.Job;
import com.levralabs.Jobboard.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/jobboard")
    public List<Job> displayJobs() {
        return jobService.displayJobs();
    }

    @RequestMapping("/get-title/{id}")
    public String getTitle(@PathVariable Long id) {
        return jobService.getTitle(id);
    }

    @RequestMapping("/get-ave/{id}")
    public Integer getAve(@PathVariable Long id) {
        return jobService.getAve(id);
    }

    @RequestMapping("/add/{id}")
    public Job addJob(@PathVariable Long id) {
        return jobService.addJob(id);
    }

    @RequestMapping("/delete/{id}")
    public Job deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }

    @RequestMapping("/empty")
    public void deleteAll() {
        jobService.deleteAll();
        return;
    }

    @RequestMapping("/expect/{id}")
    public List<Integer> expect(@PathVariable Long id) {
        return jobService.expect(id);
    }

}

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

    @RequestMapping("jobboard/get-title/{id}")
    public String getTitle(@PathVariable Long id) {
        return jobService.getTitle(id);
    }

    @RequestMapping("jobboard/get-ave/{id}")
    public Integer getAve(@PathVariable Long id) {
        return jobService.getAve(id);
    }

    @RequestMapping("jobboard/add/{id}")
    public List<Job> addJob(@PathVariable Long id) {
        return jobService.addJob(id);
    }


    @RequestMapping("jobboard/delete/{id}")
    public List<Job> deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }

    @RequestMapping("jobboard/empty")
    public List<Job> deleteAll() {
        return jobService.deleteAll();
    }

    @RequestMapping("jobboard/expect/{id}")
    public List<Integer> expect(@PathVariable Long id) {
        return jobService.expect(id);
    }

}

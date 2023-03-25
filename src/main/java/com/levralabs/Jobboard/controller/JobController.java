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

    @RequestMapping("/job-board")
    public List<Job> displayJobs() {
        return jobService.displayJobs();
    }

    @RequestMapping("job-board/get-title/{id}")
    public String getTitle(@PathVariable Long id) {
        return jobService.getTitle(id);
    }

    @RequestMapping("job-board/get-ave/{id}")
    public Integer getAve(@PathVariable Long id) {
        return jobService.getAve(id);
    }

    @RequestMapping("job-board/add/{job-title}")
    public void addJob(@PathVariable("job-title") String jobTitle) {
        jobService.addJob(jobTitle);
    }

    @RequestMapping("job-board/delete/{job-title}")
    public void deleteJob(@PathVariable("job-title") String jobTitle) {
        jobService.deleteJob(jobTitle);
    }

    @RequestMapping("job-board/empty")
    public void deleteAll() {
        jobService.deleteAll();
    }

    @RequestMapping("job-board/expect/{id}")
    public List<Integer> expect(@PathVariable Long id) {
        return jobService.expect(id);
    }

}

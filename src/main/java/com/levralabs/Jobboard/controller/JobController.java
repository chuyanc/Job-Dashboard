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

    /**
     * To view the main job dashboard
     */
    @RequestMapping("/job-board")
    public List<Job> displayJobs() {
        return jobService.displayJobs();
    }

    /**
     * To view the title of a certain job on the dashboard
     * @param id
     * @return the desired jobtitle
     */
    @RequestMapping("/job-board/get-title/{id}")
    public String getTitle(@PathVariable Long id) {
        return jobService.getTitle(id);
    }

    /**
     * To view the annual average salary of a certain job on the dashboard
     * @param id
     * @return the annual average salary
     */
    @RequestMapping("/job-board/get-ave/{id}")
    public String getAve(@PathVariable Long id) {
        return jobService.getAve(id);
    }

    /**
     * To view the data of the expected annual average salary in 5 years
     * @param id
     * @return
     */
    @RequestMapping("/job-board/expect/{id}")
    public List<Double> expect(@PathVariable Long id) {
        return jobService.expect(id);
    }

    /**
     * Add a job to the dashboard
     * @param jobTitle
     */
    @RequestMapping("/job-board/add/{job-title}")
    public void addJob(@PathVariable("job-title") String jobTitle) {
        jobService.addJob(jobTitle);
    }

    /**
     * Remove a job from the dashboard
     * @param jobTitle
     */
    @RequestMapping("/job-board/delete/{job-title}")
    public void deleteJob(@PathVariable("job-title") String jobTitle) {
        jobService.deleteJob(jobTitle);
    }

    /**
     * Clear the job dashboard
     */
    @RequestMapping("/job-board/clear")
    public void clear() {
        jobService.clear();
    }

}

package com.levralabs.Jobboard.service;

import com.levralabs.Jobboard.dao.JobRepository;
import com.levralabs.Jobboard.entity.Job;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Display the user's selected jobs to the job dashboard by the order of added time
     * @return
     */
    public List<Job> displayJobs() {
        return jobRepository.findByIsSelected(true, Sort.by(Sort.Direction.DESC, "lastUpdated"));
    }

    /**
     * Add a job to the dashboard
     * @param jobTitle
     * @return
     */
    @Transactional
    public void addJob(String jobTitle) {
        List<Job> list = jobRepository.findByJobTitle(jobTitle);
        if (list == null) {
            throw new ResourceNotFoundException("Job" + jobTitle + "not found");
        }
        for (Job job : list) {
            jobRepository.updateSelectedAsTrue(job.getId());
        }
        return;
    }


    /**
     * Remove a job from the dashboard
     * @param jobTitle
     * @return
     */
    @Transactional
    public void deleteJob(String jobTitle) {
        List<Job> list = jobRepository.findByJobTitle(jobTitle);
        if (list == null) {
            throw new ResourceNotFoundException("Job" + jobTitle + "not found");
        }
        for (Job job : list) {
            jobRepository.updateSelectedAsFalse(job.getId());
        }
        return;
    }


    /**
     * Clear the dashboard
     * @return
     */
    @Transactional
    public void deleteAll() {
        List<Job> jobList = displayJobs();
        for (Job job : jobList) {
            jobRepository.updateSelectedAsFalse(job.getId());
        }
        return;
    }

    /**
     * Calculate the expected annual average wages in 5 years of a certain job
     * @param id
     * @return
     */
    public List<Integer> expect(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        String annualAveString = job.getAnnualMean();
        int annualAve = Integer.parseInt(annualAveString.replaceAll(",", ""));
        List<Integer> nextFive = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nextFive.add(annualAve);
            annualAve *= 1.03;

        }
        return nextFive;
    }

    /**
     * Get job title of a certain job
     * @param id
     * @return
     */
    public String getTitle(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        return job.getJobTitle();
    }

    /**
     * Get annual average salary of a certain job
     * @param id
     * @return
     */
    public Integer getAve(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        return Integer.parseInt(job.getAnnualMean().replaceAll(",", ""));
    }

}

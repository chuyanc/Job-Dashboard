package com.levralabs.Jobboard.service;

import com.levralabs.Jobboard.dao.JobRepository;
import com.levralabs.Jobboard.entity.Job;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;


    /**
     * Display the user's selected jobs to the job dashboard by the order of added time
     * @return a list of jobs
     */
    public List<Job> displayJobs() {
        return jobRepository.findByIsSelected(true,
                Sort.by(Sort.Direction.DESC, "lastUpdated"));
    }

    /**
     * Get job title of a certain job
     * @param id
     * @return the title of the job
     */
    public String getTitle(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(()
                -> new ResourceNotFoundException("Data not found with id " + id));
        return job.getJobTitle();
    }

    /**
     * Get annual average salary of a certain job
     * @param id
     * @return the annual average salary of the job
     */
    public String getAve(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(()
                -> new ResourceNotFoundException("Data not found with id " + id));
        return job.getAnnualMean();
    }

    /**
     * Calculate the expected annual average wages in 5 years of a certain job
     * @param id
     * @return a list that contains the expected salary in the next 5 years
     */
    public List<Double> expect(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Data not found with id " + id));
        String annualAveString = job.getAnnualMean();
        double annualAve = Integer.parseInt(annualAveString.replaceAll(",", ""));
        List<Double> nextFive = new ArrayList<>();
        // Assume that 3% growth rate for 5 years
        for (int i = 0; i < 5; i++) {
            DecimalFormat df = new DecimalFormat("#.##");
            double round = Double.parseDouble(df.format(annualAve));
            nextFive.add(round);
            annualAve *= 1.03;
        }
        return nextFive;
    }

    /**
     * Add a job to the dashboard
     * @param jobTitle
     * @return
     */
    @Transactional
    public void addJob(String jobTitle) {
        List<Job> list = jobRepository.findByJobTitle(jobTitle);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Job" + jobTitle + "not found");
        }
        for (Job job : list) {
            jobRepository.updateSelectedAsTrue(job.getId());
        }
        return;
    }

    /**
     * Remove a job from the dashboard
     * @param id
     * @return
     */
    @Transactional
    public void deleteJob(Long id) {
        jobRepository.updateSelectedAsFalse(id);
        return;
    }

    /**
     * Clear the dashboard
     * @return
     */
    @Transactional
    public void clear() {
        List<Job> jobList = displayJobs();
        for (Job job : jobList) {
            jobRepository.updateSelectedAsFalse(job.getId());
        }
        return;
    }

}

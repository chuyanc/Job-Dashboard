package com.levralabs.Jobboard.service;

import com.levralabs.Jobboard.dao.JobRepository;
import com.levralabs.Jobboard.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
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
        return jobRepository.findBySelected(1, Sort.by(Sort.Direction.DESC, "lastUpdated"));
    }

    /**
     * Add a job to the dashboard
     * @param id
     * @return
     */
    public List<Job> addJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(1);
        job.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        jobRepository.save(job);
        return displayJobs();
    }


    /**
     * Remove a job from the dashboard
     * @param id
     * @return
     */
    public List<Job> deleteJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(0);
        jobRepository.save(job);
        return displayJobs();
    }


    /**
     * Clear the dashboard
     * @return
     */
    public List<Job> deleteAll() {
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList) {
            deleteJob((long)job.getId());
        }
        return displayJobs();
    }

    /**
     * Calculate the expected annual average wages in 5 years of a certain job
     * @param id
     * @return
     */
    public List<Integer> expect(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
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

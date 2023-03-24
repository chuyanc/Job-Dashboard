package com.levralabs.Jobboard.service;

import com.levralabs.Jobboard.dao.JobRepository;
import com.levralabs.Jobboard.entity.Job;
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
        return jobRepository.findBySelected(1, Sort.by(Sort.Direction.DESC, "lastUpdated"));
    }

    /**
     * Add a job to the dashboard
     * @param id
     * @return
     */
    public Job addJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(1);
        return jobRepository.save(job);
    }


    /**
     * Remove a job from the dashboard
     * @param id
     * @return
     */
    public Job deleteJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(0);
        return jobRepository.save(job);
    }


    /**
     * Clear the dashboard
     * @return
     */
    public void deleteAll() {
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList) {
            deleteJob((long)job.getId());
        }
        return;
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

}
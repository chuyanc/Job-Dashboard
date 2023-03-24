package com.levralab.Jobboard.service;

import com.levralab.Jobboard.dao.JobRepository;
import com.levralab.Jobboard.entity.Job;
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

    public List<Job> displayJobs() {
        return jobRepository.findBySelected(1, Sort.by(Sort.Direction.DESC, "lastUpdated"));
    }

    public Job addJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(1);
        return jobRepository.save(job);
    }


    public Job deleteJob(Long id) {
        Job job = jobRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Data not found with id " + id));
        job.setSelected(0);
        return jobRepository.save(job);
    }


    public void deleteAll() {
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList) {
            deleteJob((long)job.getId());
        }
        return;
    }

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

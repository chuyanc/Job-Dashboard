package com.levralab.Jobboard.dao;

import com.levralab.Jobboard.entity.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findBySelected(@RequestParam("selected") Integer selected, Sort lastUpdated);
}

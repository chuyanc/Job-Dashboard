package com.levralabs.Jobboard.dao;

import com.levralabs.Jobboard.entity.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByIsSelected(@RequestParam("isSelected") Boolean isSelected, Sort lastUpdated);

    List<Job> findByJobTitle(@RequestParam("jobTitle") String jobTitle);

    @Modifying
    @Query("UPDATE Job j SET j.isSelected = true, j.lastUpdated = CURRENT_TIMESTAMP WHERE j.id = :id")
    Integer updateSelectedAsTrue(@RequestParam("id") Long id);

    @Modifying
    @Query("UPDATE Job j SET j.isSelected = false, j.lastUpdated = CURRENT_TIMESTAMP WHERE j.id = :id")
    Integer updateSelectedAsFalse(@RequestParam("id") Long id);

}

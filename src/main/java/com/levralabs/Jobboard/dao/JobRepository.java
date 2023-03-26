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

    /**
     * Find the jobs with certain selected status, and return by the order of last updated time
     * @param isSelected
     * @param lastUpdated
     * @return a list of jobs that have been selected
     */
    List<Job> findByIsSelected(@RequestParam("isSelected") Boolean isSelected, Sort lastUpdated);

    /**
     * Find out all the jobs with the typed job title
     * @param jobTitle
     * @return a list of jobs of the job title
     */
    List<Job> findByJobTitle(@RequestParam("jobTitle") String jobTitle);

    /**
     * Set the selected status as true, and update the last updated time to current time
     * @param id
     * @return
     */
    @Modifying
    @Query("UPDATE Job j SET j.isSelected = true, j.lastUpdated = CURRENT_TIMESTAMP WHERE j.id = :id")
    Integer updateSelectedAsTrue(@RequestParam("id") Long id);

    /**
     * Set the selected status as false, and update the last updated time to current time
     * @param id
     * @return
     */
    @Modifying
    @Query("UPDATE Job j SET j.isSelected = false, j.lastUpdated = CURRENT_TIMESTAMP WHERE j.id = :id")
    Integer updateSelectedAsFalse(@RequestParam("id") Long id);

}

package com.levralab.Jobboard.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="job_table")

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // The occupation code
    @Column(name = "occ_code")
    private String jobCode;

    // The title of the job
    @Column(name = "occ_title")
    private String jobTitle;

    // The average hourly salary
    @Column(name = "a_mean")
    private String annualMean;

    // The status of being selected
    @Column(name = "selected")
    private Integer selected;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

}

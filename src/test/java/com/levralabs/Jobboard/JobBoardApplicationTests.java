package com.levralabs.Jobboard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.levralabs.Jobboard.controller.JobController;
import com.levralabs.Jobboard.entity.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

@SpringBootTest
class JobBoardApplicationTests {

	@Autowired
	JobController jobController;

	@Autowired
	WebApplicationContext webApplicationContext;
	MockMvc mockMvc;

	@Test
	void testController() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// At first, the job dashboard should have no job
		mockMvc.perform(get("/job-board"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());

		// Try to add one job to the dashboard
		mockMvc.perform(get("/job-board/add/{job-title}", "Computer Occupations"))
				.andExpect(status().isOk());

		// And display board again to see the new job
		mockMvc.perform(get("/job-board"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));

		List<Job> jobs = jobController.displayJobs();
		Long id1 = jobs.get(0).getId();

		// Check the new job's title
		mockMvc.perform(get("/job-board/get-title/{id}", id1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Computer Occupations"));

		// Check the new job's annual average salary
		mockMvc.perform(get("/job-board/get-ave/{id}", id1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("99,620"));

		// Check the new job's expected salary in the next 5 years
		mockMvc.perform(get("/job-board/expect/{id}", id1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(5))
				.andExpect(jsonPath("$[4]").value(112123.19));

		// Add another new job
		mockMvc.perform(get("/job-board/add/{job-title}", "Sales Managers"))
				.andExpect(status().isOk());

		// And display board again to see the new job
		mockMvc.perform(get("/job-board"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		List<Job> jobs2 = jobController.displayJobs();
		Long id2 = jobs2.get(0).getId();

		// Check if the new job displays in front of the previous one
		mockMvc.perform(get("/job-board/get-title/{id}", id2))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Sales Managers"));

		// Delete one job
		mockMvc.perform(get("/job-board/delete/{job-title}", "Sales Managers"))
				.andExpect(status().isOk());

		// Check whether the job is deleted
		mockMvc.perform(get("/job-board"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));

		// Delete all the jobs and check if the job board is empty now
		mockMvc.perform(get("/job-board/clear"))
				.andExpect(status().isOk());
		mockMvc.perform(get("/job-board"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

}

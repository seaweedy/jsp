package kr.or.ddit.jobs.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.jobs.model.JobsVo;

public class JobsServiceTest {

	@Test
	public void JobsServiceTest() {
		/***Given***/
		JobsServiceI jobsService = new JobsService();

		/***When***/
		List<JobsVo> jobsList = jobsService.getAllJobs();

		/***Then***/
		assertEquals(19, jobsList.size());
	}

}

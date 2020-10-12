package kr.or.ddit.jobs.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.jobs.model.JobsVo;

public class JobsDaoTest {

	@Test
	public void getAllJobsTest() {
		/***Given***/
		JobsDao jobsDao = new JobsDao();

		/***When***/
		List<JobsVo> jobsList = jobsDao.getAllJobs();

		/***Then***/
		assertEquals(19, jobsList.size());
	}

}

package kr.or.ddit.jobs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.jobs.model.JobsVo;
import kr.or.ddit.jobs.service.JobsService;
import kr.or.ddit.jobs.service.JobsServiceI;

@WebServlet("/getAllJobsController")
public class GetAllJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JobsServiceI jobsService = new JobsService();  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JobsVo> jobsList = jobsService.getAllJobs();
		
		request.setAttribute("jobsList", jobsList);
		
		request.getRequestDispatcher("/jobs.jsp").forward(request, response);
	}

}

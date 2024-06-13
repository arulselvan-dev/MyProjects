package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.DAO.JobDAO;
import com.DB.DBConnect;
import com.entity.Jobs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 try {
			 String title=req.getParameter("title");
			 String location=req.getParameter("location");
			 String category =req.getParameter("category");
			 String status=req.getParameter("status");
			 String desc=req.getParameter("desc");

			 Jobs j=new Jobs();
			 j.setTitle(title);
			 j.setDescription(desc);
			 j.setLocation(location);
			 j.setStatus(status);
			 j.setCategory(category);


			 jakarta.servlet.http.HttpSession session= req.getSession();

			 JobDAO dao=new JobDAO(DBConnect.getConn());
			 boolean f=dao.addJobs(j);
			 if(f){
			 session.setAttribute("succMsg","Job Post Sucessfully..");
			 resp.sendRedirect("add_job.jsp");
			 }
			 else{
			 session.setAttribute("Msg","Something wrong on server");
			 resp.sendRedirect("add_job.jsp");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
	}

}

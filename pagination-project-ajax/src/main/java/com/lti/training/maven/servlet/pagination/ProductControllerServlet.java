package com.lti.training.maven.servlet.pagination;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.group.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.training.maven.jdbc.DataAccessException;
import com.lti.training.maven.jdbc.dao.ProductDao;
import com.lti.training.maven.model.Product;

@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = 2;
		
		int currentPosition=1;
		if(request.getParameter("cp")!=null) {
			currentPosition=Integer.parseInt(request.getParameter("cp"));
		}
		
		ProductDao dao = new ProductDao();
	
		List<Product> products = dao.fetchProducts(currentPosition, currentPosition + pageSize-1);
		
		ObjectMapper mapper = new ObjectMapper();
		String productsJSON = mapper.writeValueAsString(products);
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:4200"); //Server contacting the angular server and setting header to link
		response.setHeader("Access-Control-Allow-Methods","GET"); //Setting the method as GET
		PrintWriter out = response.getWriter();
		out.write(productsJSON);
		
	}
}
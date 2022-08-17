package controller;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CrudDAO;
import model.CrudModel;

public class CrudController extends HttpServlet {
	
	CrudModel crudmodel = new CrudModel();
    CrudDAO cruddao = new CrudDAO();
	
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        if(request.getParameter("addExpense")!=null){
	            String name = request.getParameter("name");
	            int cost = Integer.parseInt(request.getParameter("cost"));
	            String date = request.getParameter("date");
	            String color = request.getParameter("color");
	            String url = request.getParameter("url");
	            String remark = request.getParameter("remark");
	            crudmodel.setName(name);
	            crudmodel.setCost(cost);
	            crudmodel.setColor(color);
	            crudmodel.setUrl(url);
	            crudmodel.setRemark(remark);
	            
	            Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				crudmodel.setDate(date1);
	            cruddao.create(crudmodel);
	            List<CrudModel> ExpenseList = new ArrayList();
	            ExpenseList = cruddao.readAll();
	            request.setAttribute("ExpenseList", ExpenseList);
	            RequestDispatcher rd = request.getRequestDispatcher("ExpenseAdd.jsp");
	            rd.forward(request, response);
	        }
	          
	        if(request.getParameter("showInfo")!=null){
	        	 int id1 = Integer.parseInt(request.getParameter("id"));
	           CrudModel expense = cruddao.read(id1);
	           request.setAttribute("record", expense);
	            List<CrudModel> ExpenseList = new ArrayList();
	            ExpenseList = cruddao.readAll();
	            request.setAttribute("ExpenseList", ExpenseList);
	            RequestDispatcher rd = request.getRequestDispatcher("ExpenseAdd.jsp");
	            rd.forward(request, response);
	        }
	        
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	         if(request.getParameter("showExpense")!=null){
	            List<CrudModel> ExpenseList = new ArrayList();
	            ExpenseList = cruddao.readAll();
	            request.setAttribute("ExpenseList", ExpenseList);
	            RequestDispatcher rd = request.getRequestDispatcher("ExpenseAdd.jsp");
	            rd.forward(request, response);
	        }
	         
	          if(request.getParameter("updateExpense")!=null){
	             int id1 = Integer.parseInt(request.getParameter("id"));
	             String nameupdate = request.getParameter("nameupdate");
	             int priceupdate = Integer.parseInt(request.getParameter("priceupdate"));
	             String date = request.getParameter("dateupdate");
	             Date dateupdate = null;
	 			try {
	 				dateupdate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
	 			} catch (ParseException e) {
	 				e.printStackTrace();
	 			}
	             String colorupdate = request.getParameter("colorupdate");
	             String urlupdate = request.getParameter("urlupdate");
	             String remarkupdate = request.getParameter("remarkupdate");
	             cruddao.update(id1, nameupdate, priceupdate, colorupdate, urlupdate ,remarkupdate, dateupdate);
	             List<CrudModel> ExpenseList = new ArrayList();
	             ExpenseList = cruddao.readAll();
	             request.setAttribute("ExpenseList", ExpenseList);
	             RequestDispatcher rd = request.getRequestDispatcher("ExpenseAdd.jsp");
	             rd.forward(request, response);
	             
	         }
	          
	         if(request.getParameter("deleteExpense")!=null){
	             int id2 = Integer.parseInt(request.getParameter("id"));
	             crudmodel.setId(id2);
	             cruddao.delete(crudmodel);
	             List<CrudModel> ExpenseList = new ArrayList();
	             ExpenseList = cruddao.readAll();
	             request.setAttribute("ExpenseList", ExpenseList);
	             RequestDispatcher rd = request.getRequestDispatcher("ExpenseAdd.jsp");
	            rd.forward(request, response);
	         }
	         
	      
	      
	    }

	 
	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>
	
	
	

}


package dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.CrudModel;

public class CrudDAO {
	

	
	public void create(CrudModel crud) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(crud);
		transaction.commit();
		session.close();
	}
	
	public void update(int id , String name, int cost,String color,String url , String remarks , Date date) {
		
		 	Session session =  HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = session.beginTransaction();
	        CrudModel details = session.load(CrudModel.class, id);
	        details.setName(name);
	        details.setCost(cost);
	        details.setColor(color);
	        details.setDate(date);
	        details.setUrl(url);
	        details.setRemark(remarks);
	        session.update(details);
	        transaction.commit();
	        session.close();
	}

	public CrudModel read(int id) {
		
		Session session =  HibernateUtil.getSessionFactory().openSession();
		CrudModel s = session.get(CrudModel.class, id);
			
		
		if(s==null)
		{
			System.out.println("Record does not exist");
		}
		else {
			return s;
				
		}
		session.close();
		return null;
		
		
	}
	
	public List<CrudModel> readAll() {
		
		 List<CrudModel> employeeList = new ArrayList();
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Query query = session.createQuery("From CrudDemo");
	        employeeList = query.list();
	        return employeeList;
		
		
	}

	public void delete(CrudModel crud) {
		// TODO Auto-generated method stub
Session session =  HibernateUtil.getSessionFactory().openSession();
		
		
		Transaction transaction  = session.beginTransaction();
	
			session.delete(crud);
		
		transaction.commit();
		session.close();
	}


}

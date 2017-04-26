package com.demo.hibernate.firstproject;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.firstproject.EnumEmployee.GENDER;

public class RepositoryService {
	public static void main(String[] args) {
		RepositoryService rs = new RepositoryService();
		Session session = rs.getSession();
		ModelEmployee me = rs.createModelEmployee();
		
		ModelEmployeeProfile mep = rs.createModelEmployeeProfile();
		
		rs.persistData(session, mep);
		
		me.setProfile(mep);
		
		rs.persistData(session, me);
		
		List<ModelEmployee> empList = rs.getEmployeeData(session);
		System.out.println(empList.get(0).getEmpName());
		
		
		List<ModelEmployeeProfile> empProfileList = rs.getEmployeeProfileData(session);
		System.out.println(empProfileList.get(0).getEmpQualification());
		
		mep.setEmpQualification("Master Degree");
		rs.updateData(session, mep);
		
		me.setProfile(mep);
		
		me.setEmpName("Hibernate-Java");
		rs.updateData(session, me);
		
		
		
		
		empList = rs.getEmployeeData(session);
		System.out.println(empList.get(0).getEmpName());
		
		
		empProfileList = rs.getEmployeeProfileData(session);
		System.out.println(empProfileList.get(0).getEmpQualification());
//		
		rs.delete(session, me);
		
		System.out.println("Deleted employee instance with id and name"+me.getId() + " "+me.getEmpName());
		if(session != null) {
			session.close();
		}
	}

	private Session getSession() {
		SessionFactory sf = new Configuration().configure("hibernate.xml").buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	private ModelEmployee createModelEmployee() {
		ModelEmployee me = new ModelEmployee();
		me.setId("1");
		me.setEmpName("Rajneesh");
		me.setEmpSalary(new BigDecimal(2000));
		return me;
	}
	
	private ModelEmployeeProfile createModelEmployeeProfile() {
		ModelEmployeeProfile mep = new ModelEmployeeProfile();
		mep.setId("1");
		mep.setEmpGender(GENDER.M);
		mep.setEmpQualification("Bachelor Degree");
		return mep;
	}
	
	private void persistData(Session session, Object object) {
		Transaction tx = session.beginTransaction();
		try {
			session.save(object);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Transaction Rolled Back due to :" + e);
		}
	}
	
	private void updateData(Session session, Object object) {
		Transaction tx = session.beginTransaction();
		try{
			session.update(object);
			tx.commit();
		}catch(Exception e) {
			System.out.println("Transaction Update rolled back due to: "+e);
		}
	}
	
	private List<ModelEmployee> getEmployeeData(Session session) {
		Query query = session.getNamedQuery(ModelEmployee.GET_EMPLOYEE_LIST);
		List<ModelEmployee> employeeList = query.getResultList();
		return employeeList;
	}
	
	private List<ModelEmployeeProfile> getEmployeeProfileData(Session session) {
		Query query = session.getNamedQuery(ModelEmployeeProfile.GET_PROFILE);
		List<ModelEmployeeProfile> listProfile = query.getResultList();
		return listProfile;
 	}
	
	private void delete(Session session, Object object) {
		Transaction tx = session.beginTransaction();
		try{
			//ModelEmployee me = session.find(ModelEmployee.class, id);
			session.delete(object);
			tx.commit();
		} catch(Exception e) {
			System.out.println("Transaction delete rolled back due to:" +e);
		}
	}
}

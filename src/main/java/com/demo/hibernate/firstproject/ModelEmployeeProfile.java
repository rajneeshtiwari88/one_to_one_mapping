package com.demo.hibernate.firstproject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.demo.hibernate.firstproject.EnumEmployee.GENDER;
@NamedQueries(@NamedQuery(name = "profile.getEmpProfile", query = "select profile from ModelEmployeeProfile profile"))
@Entity
@Table(name="employee_profile")
public class ModelEmployeeProfile extends ModelBasic{
	
	public static final String GET_PROFILE = "profile.getEmpProfile";
	
	@Column(name="emp_gender", columnDefinition="varchar(1)")
	@Enumerated(EnumType.STRING)
	private GENDER empGender;
	
	@Column(name = "emp_qualfication",columnDefinition="varchar(25)")
	private String empQualification;

	public GENDER getEmpGender() {
		return empGender;
	}

	public void setEmpGender(GENDER empGender) {
		this.empGender = empGender;
	}

	public String getEmpQualification() {
		return empQualification;
	}

	public void setEmpQualification(String empQualification) {
		this.empQualification = empQualification;
	}

}

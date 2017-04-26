package com.demo.hibernate.firstproject;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@NamedQueries({@NamedQuery(name = "employee.getEmployeeData", query = "select employee from ModelEmployee employee group by employee")})

@Entity
@Table(name="employee")
public class ModelEmployee extends ModelBasic{
	
	public static final String GET_EMPLOYEE_LIST = "employee.getEmployeeData";
	
	@Column(name = "emp_name", columnDefinition = "varchar(25)")
	private String empName;
	
	@Formula("count(*)")
	private int count;
	
	@Column(name = "emp_salary", precision = 7 , scale = 2)
	private BigDecimal empSalary;
	
	@OneToOne(fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	@JoinColumn(name="emp_profile_id",foreignKey=@ForeignKey(name="fk_emp_profile"))
	private ModelEmployeeProfile profile;
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public BigDecimal getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(BigDecimal empSalary) {
		this.empSalary = empSalary;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ModelEmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(ModelEmployeeProfile profile) {
		this.profile = profile;
	}	
	
	
}

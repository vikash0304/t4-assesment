package com.t4.assesment.app.model;

import java.util.List;

import com.t4.assesment.app.entity.Employee;

public class DepartmentEmployeesView {

	private String departmentId;
	private List<Employee> employees;

	public DepartmentEmployeesView() {
	}

	public DepartmentEmployeesView(String departmentId, List<Employee> employees) {
		super();
		this.departmentId = departmentId;
		this.employees = employees;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

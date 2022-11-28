package com.t4.assesment.app.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.t4.assesment.app.entity.Department;
import com.t4.assesment.app.entity.Employee;
import com.t4.assesment.app.model.DepartmentEmployeesView;
import com.t4.assesment.app.repository.DepartmentRepository;
import com.t4.assesment.app.repository.EmployeeRepository;

@Component
public class ReportService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public void process() {

		List<Employee> employees = employeeRepository.findAll();
		Set<Entry<Department, List<Employee>>> entrySet = groupEmployeeBasedOnDepartment(employees);
		DepartmentEmployeesView departmentEmployeesView = populateDepartmentNameToDepartmentEmployeeView(entrySet);
		List<Employee> sortedEmployees = sortEmployeeBasedonAge(employees);
		printDepartmentEmployeesView(departmentEmployeesView);
	}

	private DepartmentEmployeesView populateDepartmentNameToDepartmentEmployeeView(
			Set<Entry<Department, List<Employee>>> entrySet) {
		DepartmentEmployeesView departmentEmployeesView = new DepartmentEmployeesView();
		entrySet.forEach(e -> {
			departmentEmployeesView.setDepartmentId(e.getKey().getDepartmentId());
			departmentEmployeesView.setEmployees(e.getValue());
		});
		return departmentEmployeesView;

	}

	private Set<Entry<Department, List<Employee>>> groupEmployeeBasedOnDepartment(List<Employee> employees) {

		System.out.println("== Group the employees based on department.==");

		Map<Department, List<Employee>> employeeWithDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		Set<Entry<Department, List<Employee>>> entrySet = employeeWithDept.entrySet();
		for (Entry<Department, List<Employee>> entry : entrySet) {
			System.out.println("--------------------------------------");

			System.out.println("Employees In " + entry.getKey().getDepartmentName() + " : ");

			System.out.println("--------------------------------------");

			List<Employee> list = entry.getValue();

			for (Employee e : list) {
				System.out.println(e.getEmployeeName());
			}
		}
		return entrySet;

	}

	private List<Employee> sortEmployeeBasedonAge(List<Employee> employees) {
		System.out.println("==Sort Employee Based on Age==");
		employees.stream().sorted(Comparator.comparing(Employee::getEmployeeName).thenComparing(Employee::getAge))
				.collect(Collectors.toList()).forEach(System.out::println);
		return employees;
	}

	private void printDepartmentEmployeesView(DepartmentEmployeesView departmentEmployeesView) {
		for (Employee employee : departmentEmployeesView.getEmployees()) {
			System.out.println("DepartmentId: " + employee.getDepartment().getDepartmentId());
			System.out.println("Department Name: " + employee.getDepartment().getDepartmentName());
			System.out.println("Employee Name: " + employee.getEmployeeName());
			System.out.println("Employee Age: " + employee.getAge(employee));
		}
	}
}

package com.t4.assesment.app.service;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.t4.assesment.app.entity.Department;
import com.t4.assesment.app.entity.Employee;
import com.t4.assesment.app.repository.DepartmentRepository;
import com.t4.assesment.app.repository.EmployeeRepository;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public void process() {
		System.out.println("Add Employee Details:");
		System.out.println("Enter Employee Id:");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();

		System.out.println("Enter Employee Name:");
		String name = sc.next();
		try {
			System.out.println("Enter Employee Date of Birth in dd/mm/yyyy format:");
			String dob = sc.next();

			System.out.println("Enter Employee Department Id:");
			String depId = sc.next();
			Optional<Department> d1 = departmentRepository.findById(depId);
			if (!d1.isPresent()) {
				System.out.println("Wrong Input, Please enter correct department Id:");
				throw new IllegalArgumentException("Wrong Input.");
			}
			Employee e1 = new Employee(id, name, new Date(dob), d1.get());
			employeeRepository.saveAndFlush(e1);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}

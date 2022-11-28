package com.t4.assesment.app;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.t4.assesment.app.entity.Department;
import com.t4.assesment.app.entity.Employee;
import com.t4.assesment.app.repository.DepartmentRepository;
import com.t4.assesment.app.repository.EmployeeRepository;
import com.t4.assesment.app.service.DepartmentService;
import com.t4.assesment.app.service.EmployeeService;
import com.t4.assesment.app.service.ReportService;

@SpringBootApplication
public class T4AssesmentApplication {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(T4AssesmentApplication.class, args);
		int input = populateMenu();
		switch (input) {

		case 1:
			DepartmentService departmentService = context.getBean(DepartmentService.class);
			departmentService.process();
			break;

		case 2:
			EmployeeService employeeService = context.getBean(EmployeeService.class);
			employeeService.process();
			break;

		case 3:
			ReportService article = context.getBean(ReportService.class);
			article.process();
			break;

		default:
			System.out.println("Wront Menu selection.. Please enter correct menu.");
			System.exit(0);
		}

	}

	private static int populateMenu() {
		System.out.println("== T4 Assesment Program Menu:==\n------------------");
		System.out.println("1. Create Department.");
		System.out.println("2. Create Employee.");
		System.out.println("3. Report- Department wise Employee List.");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		return input;
	}

	@PostConstruct
	public void populateTable() {
		Department d1 = new Department("D001", "Accounts");
		Department d2 = new Department("D002", "Sales");
		Department d3 = new Department("D003", "HR");
		departmentRepository.saveAll(Arrays.asList(d1, d2, d3));

		Employee e1 = new Employee("E0001", "john", new Date("21/01/2000"), d1);
		Employee e2 = new Employee("E0002", "Ram", new Date("21/01/1985"), d1);
		Employee e3 = new Employee("E0003", "Sanjay", new Date("01/11/1995"), d2);
		employeeRepository.saveAll(Arrays.asList(e1, e2, e3));

	}

}

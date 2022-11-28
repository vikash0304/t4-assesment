package com.t4.assesment.app.service;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.t4.assesment.app.entity.Department;
import com.t4.assesment.app.repository.DepartmentRepository;

@Component
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public void process() {
		System.out.println("Add Department Details:");
		System.out.println("Enter Department Id:");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		
		System.out.println("Enter Department Name:");
		String name = sc.next();
		Department d1 = new Department(id, name);
		departmentRepository.saveAndFlush(d1);
	}

}

package com.t4.assesment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.t4.assesment.app.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}

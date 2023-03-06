package com.techacademy.service;

import java.util.List;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

public class EmployeeService {
	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public List<Employee> getEmployeeList() {

		return repository.findAll();
	}
}
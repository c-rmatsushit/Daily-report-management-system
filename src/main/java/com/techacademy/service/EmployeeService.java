package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository repository) {
		this.employeeRepository = repository;
	}

	public List<Employee> getEmployeeList() {

		return employeeRepository.findAll();
	}

	public Employee getEmployee(Integer id) {
		return employeeRepository.findById(id).get();
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
		LocalDateTime now = LocalDateTime.now();
		employee.setCreatedAt(now);
		employee.setUpdatedAt(now);
		return employeeRepository.save(employee);
	}

	@Transactional
	public void deleteEmployee(Set<Integer> idck) {
		for (Integer id : idck) {
			employeeRepository.deleteById(id);
		}
	}

}
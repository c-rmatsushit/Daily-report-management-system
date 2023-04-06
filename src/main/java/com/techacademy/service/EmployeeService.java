package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public EmployeeService(EmployeeRepository repository) {

		this.employeeRepository = repository;
	}

	public List<Employee> getEmployeeList() {
		return employeeRepository.findAll();

	}

	public Employee getEmployee(Integer id) {
		Optional<Employee> option = employeeRepository.findById(id);
		Employee employee = option.orElse(null);
		employee.getAuthentication().getCode();
		employee.getAuthentication().getRole();
		return employeeRepository.findById(id).get();
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
		LocalDateTime now = LocalDateTime.now();
		employee.setCreatedAt(now);
		employee.setUpdatedAt(now);
		employee.setDeleteFlag(0);
		employee.getAuthentication().setEmployee(employee);
		String password = employee.getAuthentication().getPassword();
		employee.getAuthentication().setPassword(passwordEncoder.encode(password));
		return employeeRepository.save(employee);
	}


	@Transactional
	public void deleteEmployee(Integer id) {

	employeeRepository.deleteById(id);

	}

	public Employee updateEmployee(Integer id, String name) {
		Optional<Employee> option = employeeRepository.findById(id);
		Employee employee = option.orElse(null);
		employee.getAuthentication().getCode();
		employee.getAuthentication().getRole();
		return employee;
	}
}

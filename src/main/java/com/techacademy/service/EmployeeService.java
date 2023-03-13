package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		Optional<Employee> option = employeeRepository.findById(id);
		Employee employee = option.orElse(null);
		return employee;
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
		LocalDateTime now = LocalDateTime.now();
		employee.setCreatedAt(now);
		employee.setUpdatedAt(now);
		return employeeRepository.save(employee);
	}



	@Transactional
	public void registerEmployee(Set<Integer> idck) {
		for (Integer id : idck) {
			employeeRepository.deleteById(id);
		}
	}

	@Transactional
	public void deleteEmployee(Set<Integer> idck) {
		for (Integer id : idck) {
			employeeRepository.deleteById(id);
		}
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


	public void updateEmployee(Integer id, String name) {
		// TODO 自動生成されたメソッド・スタブ

	}


}
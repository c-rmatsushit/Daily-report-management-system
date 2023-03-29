package com.techacademy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;

	public ReportService(ReportRepository repository) {
		this.reportRepository = repository;
	}

	public List<Report> getReportList() {
		return reportRepository.findAll();
	}

	public  List<Report> getReportListEmployee(Employee employee) {
		return reportRepository.findByEmployee(employee);
	}

	public Report getReport(Integer id) {
		Optional<Report> option = reportRepository.findById(id);
		Report report = option.orElse(null);
		report.getEmployee().getName();
		return report;
	}

	@Transactional
	public Report saveReport(Report report) {
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = LocalDate.now();

		if (report.getReportDate() == null) {
			report.setReportDate(today);
		}

		report.setCreatedAt(now);
		report.setUpdatedAt(now);
		report.getEmployee().getName();
		return reportRepository.save(report);
	}

	@Transactional
	public void registerReport(Set<Integer> idck) {
		for (Integer id : idck) {
			reportRepository.deleteById(id);
		}
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Report updateReport(Integer id, String name) {
		Optional<Report> option = reportRepository.findById(id);
		Report report = option.orElse(null);
		return report;
	}

}
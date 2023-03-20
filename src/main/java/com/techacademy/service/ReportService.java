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

	@Transactional
	public Report saveReport(Report report) {
		return reportRepository.save(report);
	}

	public Report getReport(Integer id) {
		Optional<Report> option = reportRepository.findById(id);
		Report report = option.orElse(null);
		report.getEmployee().getName();
		return report;
	}

	@Transactional
	public Report saveRreport(Report report) {
		LocalDateTime now = LocalDateTime.now();
		LocalDate date1 = LocalDate.now();
		report.setReportDate(date1);
		report.setCreatedAt(now);
		report.setUpdatedAt(now);
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
		report.getEmployee().getName();
		return report;
	}
}
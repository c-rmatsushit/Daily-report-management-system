package com.techacademy.service;

import java.util.List;

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
}

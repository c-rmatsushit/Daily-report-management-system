package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.ReportService;

@Controller
@RequestMapping("report")
public class ReportController {
	private final ReportService service;

	public ReportController(ReportService service) {
		this.service = service;
	}

	@GetMapping("/list")
	public String getList(Model model) {

		model.addAttribute("reportlist", service.getReportList());

		return "report/list";

	}
}

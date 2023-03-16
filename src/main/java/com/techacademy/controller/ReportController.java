package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Report;
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

	@GetMapping(value = { "/detail", "/detail/{id}/" })
	public String getReportdetail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("report", service.getReport(id));
		return "report/detail";
	}

	@PostMapping("/detail")
	public String postReport(@RequestParam("id") Integer id, @RequestParam("name") String name, Model model) {
		service.updateReport(id, name);
		return "redirect:/report/list";
	}

	@GetMapping("/register")
	public String getRegister(@ModelAttribute Report report) {
		return "report/register";
	}

	@PostMapping("/register")
	public String postRegister(@Validated Report report, BindingResult res, Model model) {
		if (res.hasErrors()) {

			return getRegister(report);
		}

		service.saveReport(report);

		return "redirect:/report/list";
	}

	@GetMapping("/update/{id}/")
	public String getReport(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("report", service.getReport(id));

		return "report/update";
	}

	@PostMapping("/update/{id}/")
	public String postReport(Report report) {

		service.saveReport(report);

		return "redirect:/report/list";
	}

	@GetMapping("/report")
	public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		 String username = userDetails.getUsername();
		    model.addAttribute("username", username);
		    return "report/register";
	}
}

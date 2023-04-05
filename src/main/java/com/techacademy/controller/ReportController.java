package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
	private final ReportService service;

	public ReportController(ReportService service) {
		this.service = service;

	}
	@GetMapping("/mylist")
	public String getMylist(@ModelAttribute Report report ,@AuthenticationPrincipal UserDetail userDetail, Model model) {
	    String username = userDetail.getUsername();
	    model.addAttribute("username", username);
	    model.addAttribute("report", report);
		model.addAttribute("reportlist", service.getReportListEmployee(userDetail.getUser()));
		return "report/mylist";

	}

	@GetMapping("/list")
	public String getList(@ModelAttribute Report report ,@AuthenticationPrincipal UserDetail userDetail, Model model) {
	    String username = userDetail.getUsername();
	    model.addAttribute("username", username);
	    model.addAttribute("report", report);
		model.addAttribute("reportlist", service.getReportList());
		return "report/list";

	}

	@GetMapping("/register")
	public String getRegister(@ModelAttribute Report report ,@AuthenticationPrincipal UserDetail userDetail, Model model) {
	    String username = userDetail.getUsername();
	    Employee user = userDetail.getUser();
	    model.addAttribute("user", user);
	    model.addAttribute("username", username);
	    model.addAttribute("report", report);
	    return "report/register";
	  }


	@PostMapping("/register")
	 public String postRegister(@Validated Report report, BindingResult res ,@AuthenticationPrincipal UserDetail userDetail, Model model) {
			if (res.hasErrors()) {

				return getRegister( report, userDetail, model);
			}
			report.setEmployee(userDetail.getUser());
			LocalDateTime now = LocalDateTime.now();
			report.setCreatedAt(now);
			report.setUpdatedAt(now);
			service.saveReport(report);

			return "redirect:/report/list";
	}



	@GetMapping("/update/{id}/")
	public String getReport(@AuthenticationPrincipal UserDetail userDetail,@PathVariable("id") Integer id, Model model) {
		model.addAttribute("report", service.getReport(id));
		String username = userDetail.getUsername();
	    model.addAttribute("username", username);
		return "report/update";
	}

	@PostMapping("/update/{id}/")
	public String postReport(@PathVariable("id") Integer id,@Validated Report report, BindingResult res ,@AuthenticationPrincipal UserDetail userDetail, Model model) {
		if (res.hasErrors()) {

			return getRegister( report, userDetail, model);
		}
		report.setEmployee(userDetail.getUser());
		Report updatereport = service.getReport(id);
		LocalDateTime now = LocalDateTime.now();
		updatereport.setUpdatedAt(now);
		service.saveReport(updatereport);
		return "redirect:/report/list";
	}


    @GetMapping(value = { "/detail", "/detail/{id}/" })
    public String getReportdetail(@AuthenticationPrincipal UserDetail userDetail,@PathVariable("id") Integer id, Model model) {
		model.addAttribute("report", service.getReport(id));
		String username = userDetail.getUsername();
		Employee user = userDetail.getUser();
	    model.addAttribute("user", user);
	    model.addAttribute("username", username);
        return "report/detail";
    }

	@PostMapping("/detail")
	public String postReport(@Validated Report report ,@RequestParam("id") Integer id, @RequestParam("name") String name, Model model) {
		service.updateReport(id, name);
		return "redirect:/report/list";
	}
}

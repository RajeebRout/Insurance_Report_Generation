package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nt.service.IServiceCitizen;

@Controller
public class ReportController 
{
	@Autowired
	private IServiceCitizen service;
	
	@GetMapping("/")
	public String indexPage()
	{
		return "index";
	}
}

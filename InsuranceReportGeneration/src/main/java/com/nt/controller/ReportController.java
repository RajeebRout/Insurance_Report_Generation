package com.nt.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.entity.CitizenPlan;
import com.nt.request.SearchRequest;
import com.nt.service.IServiceCitizen;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController 
{
	@Autowired
	private IServiceCitizen service;
	
	@GetMapping("/")
	public String indexPage(Map<String,Object> map,@ModelAttribute("search") SearchRequest sereq)
	{	
		map.put("names", service.getPlanNames());
		map.put("status", service.getPlanStatus());
		return "index";
	}
	
	@PostMapping("/searchPost")
	public String searchData(Map<String,Object> map,@ModelAttribute("search") SearchRequest sereq)
	{
		List<CitizenPlan> searchList = service.searchRecord(sereq);
		
		map.put("names", service.getPlanNames());
		map.put("status", service.getPlanStatus());
		
		map.put("recordList", searchList);
		
		return "index";
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		//response.addHeader("Content-Disposition", "attachment;filename=plans.xlsx;");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls;");
		
		service.exportExcel(response);
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf;");
		
		service.exportPdf(response);
	}
}

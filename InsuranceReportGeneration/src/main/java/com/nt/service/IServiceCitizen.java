package com.nt.service;

import java.io.IOException;
import java.util.List;

import org.openpdf.text.DocumentException;

import com.nt.entity.CitizenPlan;
import com.nt.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface IServiceCitizen 
{
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> searchRecord(SearchRequest request);	
	
	public boolean exportExcel(HttpServletResponse response) throws IOException;
	public boolean exportPdf(HttpServletResponse response) throws Exception;
}

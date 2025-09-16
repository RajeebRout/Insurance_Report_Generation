package com.nt.service;

import java.util.List;

import com.nt.entity.CitizenPlan;
import com.nt.request.SearchRequest;

public interface IServiceCitizen 
{
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> searchRecord(SearchRequest request);
	
	public boolean exportExcel();
	public boolean exportPdf();
}

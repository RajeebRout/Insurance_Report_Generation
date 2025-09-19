package com.nt.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.entity.CitizenPlan;
import com.nt.repo.CitizenPlanRepo;
import com.nt.request.SearchRequest;
import com.nt.util.EmailsUtils;
import com.nt.util.ExcelGenerator;
import com.nt.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenServiceImpl implements IServiceCitizen {

	@Autowired
	private CitizenPlanRepo repo;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private EmailsUtils emailUtil;
	
	String subject="Test Mail Subject";
	String body="<h1>Test Mail Body</h1>";
	String to = "suhanishah11111@gmail.com";
		
	@Override
	public List<String> getPlanNames() 
	{
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() 
	{
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchRecord(SearchRequest request) 
	{
		CitizenPlan entity=new CitizenPlan();
		if(request.getPlanName()!= null && ! "".equals(request.getPlanName()))
		{
			entity.setPlanName(request.getPlanName());
		}
		if(request.getPlanStatus()!= null && ! "".equals(request.getPlanStatus()))
		{
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(request.getGender()!= null && ! "".equals(request.getGender()))
		{
			entity.setGender(request.getGender());
		}
		return repo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws IOException 
	{
		File file=new File("Plans.xls");
		
		List<CitizenPlan> recordList = repo.findAll();
		
		excelGenerator.generateExcel(response, recordList,file);
		
		emailUtil.sendEmail(subject, body, to,file);
		
		file.delete();
		
		return true;	
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception
	{
		File f=new File("Plans.pdf");
		
		List<CitizenPlan> recordList = repo.findAll();
		
		pdfGenerator.generatePdf(response, recordList,f);
		
		emailUtil.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
	}

}

package com.nt.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.entity.CitizenPlan;
import com.nt.repo.CitizenPlanRepo;
import com.nt.request.SearchRequest;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenServiceImpl implements IServiceCitizen {

	@Autowired
	private CitizenPlanRepo repo;
	
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
		//Workbook workbook = new XSSFWorkbook();	
		Workbook workbook = new HSSFWorkbook();	
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");
		
		List<CitizenPlan> list = repo.findAll();
		int dataRowIndex=1;
		
		for(CitizenPlan plan:list)
		{
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			
			if(plan.getPlanStartDate() != null)
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			else
				dataRow.createCell(4).setCellValue("N/A");
			
			if(plan.getPlanEndDate() != null)
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			else
				dataRow.createCell(5).setCellValue("N/A");
			
			if(plan.getBenefitAmount() != null)
				dataRow.createCell(6).setCellValue(plan.getBenefitAmount());
			else
				dataRow.createCell(6).setCellValue("N/A");
				
			dataRowIndex++;
		}	
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return true;	
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception
	{
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
			
		document.open();
		
		// Creating Font and setting style and size
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		
		// Creating Paragraph
		Paragraph paragraph = new Paragraph("Citizen Plans Info",fontTitle);
		
		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		// Adding the created paragraph in document
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(6);
		table.setSpacingBefore(5);
		
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		
		List<CitizenPlan> list = repo.findAll();
		for(CitizenPlan plan :list)
		{
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
		}
		
		document.add(table);
		document.close();
		
		return true;
	}

}

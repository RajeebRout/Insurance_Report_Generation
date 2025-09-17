package com.nt.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.entity.CitizenPlan;
import com.nt.repo.CitizenPlanRepo;
import com.nt.request.SearchRequest;

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
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}

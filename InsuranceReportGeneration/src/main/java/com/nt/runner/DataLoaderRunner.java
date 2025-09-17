package com.nt.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.CitizenPlan;
import com.nt.repo.CitizenPlanRepo;

@Component
public class DataLoaderRunner implements ApplicationRunner
{
	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
		repo.deleteAll();
		
		// Cash Plan Data
		CitizenPlan c1= new CitizenPlan();
		c1.setCitizenName("Sham Prasad");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approaved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(400.0);
		
		// Cash Plan Data
		CitizenPlan c2= new CitizenPlan();
		c2.setCitizenName("Ram Prasad");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income");
		
		// Cash Plan Data
		CitizenPlan c3= new CitizenPlan();
		c3.setCitizenName("Lallu Prasad");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(500.0);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Employed");
		
		// Food Plan Data
		CitizenPlan c4= new CitizenPlan();
		c4.setCitizenName("Supriya");
		c4.setGender("Female");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approaved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(400.0);	
				
		// Food Plan Data
		CitizenPlan c5= new CitizenPlan();
		c5.setCitizenName("Suresh");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Property Income");
				
		// Food Plan Data
		CitizenPlan c6= new CitizenPlan();
		c6.setCitizenName("Mukesh");
		c6.setGender("Male");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(6));
		c6.setPlanEndDate(LocalDate.now().plusMonths(4));
		c6.setBenefitAmount(500.0);
		c6.setTerminationDate(LocalDate.now());
		c6.setTerminationReason("Employed");		
		
		// Medical Plan Data
		CitizenPlan c7= new CitizenPlan();
		c7.setCitizenName("Mahesh");
		c7.setGender("Female");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approaved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(400.0);	
				
		// Medical Plan Data
		CitizenPlan c8= new CitizenPlan();
		c8.setCitizenName("Ramesh");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Property Income");
				
		// Medical Plan Data
		CitizenPlan c9= new CitizenPlan();
		c9.setCitizenName("Mukesh");
		c9.setGender("Female");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(6));
		c9.setPlanEndDate(LocalDate.now().plusMonths(4));
		c9.setBenefitAmount(500.0);
		c9.setTerminationDate(LocalDate.now());
		c9.setTerminationReason("Government Job");
		
		// Employment Plan Data
		CitizenPlan c10= new CitizenPlan();
		c10.setCitizenName("Rajesh");
		c10.setGender("Female");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approaved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmount(400.0);	
				
		// Employment Plan Data
		CitizenPlan c11= new CitizenPlan();
		c11.setCitizenName("Muskan");
		c11.setGender("Female");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Property Income");
				
		// Employment Plan Data
		CitizenPlan c12= new CitizenPlan();
		c12.setCitizenName("Brijech");
		c12.setGender("Male");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(6));
		c12.setPlanEndDate(LocalDate.now().plusMonths(4));
		c12.setBenefitAmount(500.0);
		c12.setTerminationDate(LocalDate.now());
		c12.setTerminationReason("Government Job");
		
		List<CitizenPlan> list= Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		repo.saveAll(list);
	}

}

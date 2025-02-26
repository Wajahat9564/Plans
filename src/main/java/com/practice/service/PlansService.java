package com.practice.service;

import java.util.List;
import java.util.Map;

import com.practice.dto.PlanDetailsDTO;
import com.practice.entity.Plans;

public interface PlansService
{
	public Map<Integer, String> getPlanCategory();
	
	public boolean save(Plans plan);
	
	public List<Plans> getAllPlans();
	
	public Plans getPlansById(Integer id);
	
	public boolean updatePlan(Plans plan);
	
	public boolean deletePlansById(Integer id);
	
	public boolean changeStatus(Integer id, String status);
	
	public List<PlanDetailsDTO> getPlanDetails();
}

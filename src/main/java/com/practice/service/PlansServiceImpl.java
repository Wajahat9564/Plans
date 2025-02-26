package com.practice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dto.PlanDetailsDTO;
import com.practice.entity.PlanCategory;
import com.practice.entity.Plans;
import com.practice.repository.PlanCategoryRepo;
import com.practice.repository.PlansRepo;

@Service
public class PlansServiceImpl implements PlansService
{
	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Autowired
	private PlansRepo plansRepo;

	@Override
	public Map<Integer, String> getPlanCategory() {
		List<PlanCategory> findCategory = planCategoryRepo.findAll();
		Map<Integer, String> categories = new HashMap<>();

		findCategory.forEach(category -> {
			categories.put(category.getCategoryId(), category.getCategoryName());

		});

		return categories;
	}

	@Override
	public boolean save(Plans plan) {
		Plans save = plansRepo.save(plan);
		return save != null;
	}

	@Override
	public List<Plans> getAllPlans() {
		return  plansRepo.findAll();
	}

	@Override
	public Plans getPlansById(Integer id) {
		Optional<Plans> byId = plansRepo.findById(id);
		if(byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plans plan) {
		Plans updPlan = plansRepo.save(plan);
		return updPlan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlansById(Integer id) {
		boolean deleted = false;
		try {
			plansRepo.deleteById(id);
			deleted = true;
		}
		catch (Exception e) {
			deleted = false;
		}
		return deleted;
	}

	@Override
	public boolean changeStatus(Integer id, String status) {
		Optional<Plans> byId = plansRepo.findById(id);
		if(byId.isPresent()) {
			Plans plans = byId.get();
			plans.setActiveSw(status);
			plansRepo.save(plans);
			return true;
		}
		return false;
	}

	@Override
	public List<PlanDetailsDTO> getPlanDetails() {
	    List<Object[]> result = plansRepo.fetchAllPlanDetails();
	    return result.stream()
	            .map(obj -> new PlanDetailsDTO(
	                    (Integer) obj[0],  // planId
	                    (String) obj[1],   // planName
	                    convertToLocalDate(obj[2]), // startDate
	                    convertToLocalDate(obj[3]), // endDate
	                    (Integer) obj[4],  // categoryId
	                    (String) obj[5],   // categoryName
	                    (String) obj[6],   // activeSw
	                    (String) obj[7],   // createdBy
	                    (String) obj[8],   // updatedBy
	                    convertToLocalDate(obj[9]), // createdDate
	                    convertToLocalDate(obj[10]) // updatedDate
	            )).collect(Collectors.toList());
	}

	// Helper method to safely convert java.sql.Date to java.time.LocalDate
	private LocalDate convertToLocalDate(Object obj) {
	    if (obj instanceof Date) {
	        return ((Date) obj).toLocalDate();
	    }
	    return null; // Handle null values safely
	}
}

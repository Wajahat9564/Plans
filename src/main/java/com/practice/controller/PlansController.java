package com.practice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.constant.AppConstant;
import com.practice.dto.PlanDetailsDTO;
import com.practice.entity.Plans;
import com.practice.props.AppProps;
import com.practice.service.PlansService;

@RestController
public class PlansController {
	
	private PlansService planService;
	
	private Map<String, String> messages;
	
	public PlansController(PlansService planService, AppProps props)
	{
		this.planService = planService;
		this.messages = props.getMessages();
	}
	
	@GetMapping("/category")
	public ResponseEntity<Map<Integer, String>> getAllCategories()
	{
		Map<Integer, String> planCategory = planService.getPlanCategory();
		return new ResponseEntity<>(planCategory,HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlans(@RequestBody Plans plans)
	{
		String msg = AppConstant.EMPTY_STR;
		boolean save = planService.save(plans);
		if(save)
		{
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plans>> getAllPlans()
	{
		List<Plans> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	@GetMapping("/plans/{planId}")
	public ResponseEntity<Plans> edit(@PathVariable Integer id)
	{
		Plans plansById = planService.getPlansById(id);
		return new ResponseEntity<>(plansById, HttpStatus.OK);
	}
	
	@PutMapping("/plans")
	public ResponseEntity<String> updatePlans(@RequestBody Plans plans)
	{
		String msg = AppConstant.EMPTY_STR;
		boolean update = planService.updatePlan(plans);
		if(update)
		{
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/plans/{planId}")
	public ResponseEntity<String> deletePlans(@PathVariable Integer id)
	{
		String msg = AppConstant.EMPTY_STR;
		boolean deleted = planService.deletePlansById(id);
		if(deleted)
		{
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/plans/{planId}{status}")
	public ResponseEntity<String> changePlanStatus(@PathVariable Integer id, @PathVariable String status)
	{
		String msg = AppConstant.EMPTY_STR;
		boolean changed = planService.changeStatus(id,status);
		if(changed)
		{
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/planCategory")
	public ResponseEntity<List<PlanDetailsDTO>> getDetails(){
		List<PlanDetailsDTO> planDetails = planService.getPlanDetails();
		return new ResponseEntity<>(planDetails, HttpStatus.OK);
	}
	
}

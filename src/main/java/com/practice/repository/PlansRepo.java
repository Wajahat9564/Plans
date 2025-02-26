package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.entity.Plans;

@Repository
public interface PlansRepo extends JpaRepository<Plans, Integer>
{
	@Query(value = "SELECT p.plan_id, p.plan_name, p.start_date, p.end_date, pc.category_id, pc.category_name, p.active_sw, p.created_by, p.updated_by, p.created_date, p.updated_date FROM Plan_Master p JOIN Plan_Category pc ON p.plan_category_id = pc.category_id", nativeQuery = true)
	List<Object[]> fetchAllPlanDetails();
}


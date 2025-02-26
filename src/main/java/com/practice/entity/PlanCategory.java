package com.practice.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Plan_Category")
@Data
public class PlanCategory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String categoryName;
	
	private String activeSw;
	
	@Column(updatable = false)
	private String createdBy;
	
	@Column(insertable = false)
	private String updatedBy;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	
	@CreationTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
}

package com.practice.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanDetailsDTO {  
	private Integer planId;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer categoryId;
    private String categoryName;
    private String activeSw;
    private String createdBy;
    private String updatedBy;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}

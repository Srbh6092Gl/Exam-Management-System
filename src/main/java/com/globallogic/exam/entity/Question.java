package com.globallogic.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name="question",
		uniqueConstraints = {
				@UniqueConstraint(columnNames="ques")
		})
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=120)
	private String ques;
	
	@NotBlank
	@Min(1)
	@Max(4)
	private int ansOption;
	
	@NotBlank
	@Size(max=120)
	private String option1;
	
	@NotBlank
	@Size(max=120)
	private String option2;
	
	@NotBlank
	@Size(max=120)
	private String option3;
	
	@NotBlank
	@Size(max=120)
	private String option4;
	
}

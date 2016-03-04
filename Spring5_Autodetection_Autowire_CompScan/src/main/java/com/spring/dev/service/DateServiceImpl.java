package com.spring.dev.service;

import org.joda.time.LocalDate;

import org.springframework.stereotype.Service;

// @Service marks this class as Auto-detectable bean on business layer. 
// We will inject this class into main service bean.

@Service("dateService")
public class DateServiceImpl implements DateService
{

	public LocalDate getNextAssessmentDate()
	{
		// YYYY, MM, DD format
		
		return new LocalDate(2015, 10, 15);
	}

}
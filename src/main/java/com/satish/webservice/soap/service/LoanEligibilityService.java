package com.satish.webservice.soap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satish.webservice.soap.eligibility.Acknowledment;
import com.satish.webservice.soap.eligibility.CustomerRequest;

@Service
public class LoanEligibilityService {

	public Acknowledment checkLoanEligibility(CustomerRequest request) {
		Acknowledment ack = new Acknowledment();
		List<String> mismatches = ack.getCriteriaMismatch();
		
		if(!(request.getAge() > 30 && request.getAge() < 60)) {
			mismatches.add("Person age should be in between 30 to 60");
		}
		
		if(!(request.getYearlyIncome() > 200000)) {
			mismatches.add("Yearly minimum income is 2 Lakh");
		}
		
		if(!(request.getCibilScore() > 500)) {
			mismatches.add("Cibil score should be more than 500");
		}
		
		if(mismatches.size() > 0) {
			ack.setApprovedAmount(0);
			ack.setIsEligible(false);
		} else {
			ack.setApprovedAmount(1000000);
			ack.setIsEligible(true);
			mismatches.clear();
		}
		
		return ack;
		
	}
}

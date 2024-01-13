package com.satish.webservice.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.satish.webservice.soap.eligibility.Acknowledment;
import com.satish.webservice.soap.eligibility.CustomerRequest;
import com.satish.webservice.soap.service.LoanEligibilityService;

@Endpoint
public class LoanEligibilityEndpoint{

	private static final String NAMESPACE = "http://www.satish.com/webservice/soap/eligibility";
	
	@Autowired
	LoanEligibilityService loanEligibilityService;
	

	@PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
	public @ResponsePayload Acknowledment getLoanStatus(@RequestPayload CustomerRequest request) {
		return loanEligibilityService.checkLoanEligibility(request);
	}

}

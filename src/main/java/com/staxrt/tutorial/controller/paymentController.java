package com.staxrt.tutorial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.staxrt.tutorial.model.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staxrt.tutorial.exception.ResourceNotFoundException;

import com.staxrt.tutorial.repository.paymentRepository;


/**
 * @author alanz 
 */
@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/v1")
public class paymentController {
	@Autowired
	private paymentRepository paymentRepository;

	@GetMapping("/payments")
	public List<payment> getAllpayments() {
		return paymentRepository.findAll();
	}

	@GetMapping("/payments/{id}")
	public ResponseEntity<payment> getpaymentById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("payment not found for this id : " + id));
		return ResponseEntity.ok().body(payment);
	}

	@PostMapping("/payments")
	public payment createpayment(@Valid @RequestBody payment payment) {
		return paymentRepository.save(payment);
	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<payment> updatepayment(@PathVariable(value = "id") Long id,
			@Valid @RequestBody payment paymentDetails) throws ResourceNotFoundException {
		payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("payment not found for this id : " + id));

		payment.setCustomernumber(paymentDetails.getCustomernumber());
		payment.setChecknumber(paymentDetails.getChecknumber());
		payment.setPaymentdate(paymentDetails.getPaymentdate());
		payment.setAmount(paymentDetails.getAmount());
		
		final payment updatedpayment = paymentRepository.save(payment);
		return ResponseEntity.ok(updatedpayment);
	}

	@DeleteMapping("/payments/{id}")
	public Map<String, Boolean> deletepayment(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("payment not found for this id : " + id));
	
		paymentRepository.delete(payment);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}   
	
}   
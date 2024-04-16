package com.staxrt.tutorial.controller; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.staxrt.tutorial.model.bkorder;

import com.staxrt.tutorial.repository.bkorderRepository;

import com.staxrt.tutorial.exception.ResourceNotFoundException;

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

   
@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/v1")
public class bkorderController {
	
	@Autowired
	private bkorderRepository bkorderRepository;

	@GetMapping("/bkorders")
	public List<bkorder> getAllorders() {
		return bkorderRepository.findAll();
	}

	@GetMapping("/bkorders/{ordernumber}")
	public ResponseEntity<bkorder> getbkorderById(@PathVariable(value = "ordernumber") long ordernumber,
			@Valid @RequestBody bkorder bkorderDetails) throws ResourceNotFoundException {
		bkorder bkorder = bkorderRepository.findById(ordernumber)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + ordernumber));

		return ResponseEntity.ok().body(bkorder);
	}

	@PostMapping("/bkorders")
	public bkorder createbkorder(@Valid @RequestBody bkorder bkorder) {
		return bkorderRepository.save(bkorder);
	}

	@PutMapping("/bkorders/{ordernumber}")
	public ResponseEntity<bkorder> updatebkorder(@PathVariable(value = "ordernumber") Long ordernumber,
			@Valid @RequestBody bkorder bkorderDetails) throws ResourceNotFoundException {
		bkorder bkorder = bkorderRepository.findById(ordernumber)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + ordernumber));

		bkorder.setOrdernumber(bkorderDetails.getOrdernumber()); 
		bkorder.setOrderdate(bkorderDetails.getOrderdate());
		bkorder.setRequireddate(bkorderDetails.getRequireddate());
		bkorder.setShippeddate(bkorderDetails.getShippeddate());
		bkorder.setStatus(bkorderDetails.getStatus());
		bkorder.setComments(bkorderDetails.getComments());
		bkorder.setCustomernumber(bkorderDetails.getCustomernumber());
		
		final bkorder updatedbkorder = bkorderRepository.save(bkorder);
		return ResponseEntity.ok(updatedbkorder);
	}

	@DeleteMapping("/bkorders/{ordernumber}")
	public Map<String, Boolean> deletebkorder(@PathVariable(value = "ordernumber") Long ordernumber)
			throws ResourceNotFoundException {
		bkorder bkorder = bkorderRepository.findById(ordernumber)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + ordernumber));

		bkorderRepository.delete(bkorder);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}	
	
} 

package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepository extends JpaRepository<payment, Long>{

}

package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<book, Long>{

}

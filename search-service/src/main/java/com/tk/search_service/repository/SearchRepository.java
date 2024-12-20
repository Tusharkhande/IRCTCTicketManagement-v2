package com.tk.search_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tk.search_service.model.Routes;

public interface SearchRepository extends JpaRepository<Routes, Integer>{
	
}

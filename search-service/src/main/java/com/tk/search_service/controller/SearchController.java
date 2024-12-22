package com.tk.search_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tk.search_service.dto.SearchRequest;
import com.tk.search_service.dto.SearchResponse;
import com.tk.search_service.service.SearchService;

@RestController
@RequestMapping("/api/train/search")
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@GetMapping("")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest searchRequest){
		List<SearchResponse> response = searchService.search(searchRequest);
		return ResponseEntity.ok(response);
	}
	
}

package com.tk.search_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tk.search_service.dto.Route;
import com.tk.search_service.dto.SearchRequest;
import com.tk.search_service.dto.SearchResponse;
import com.tk.search_service.dto.Stations;
import com.tk.search_service.model.Routes;
import com.tk.search_service.repository.SearchRepository;

@Service
public class SearchService {
	@Autowired
	SearchRepository repository;
	
	public List<SearchResponse> search(SearchRequest searchRequest){
		List<Route> routes = repository.findTrains(searchRequest.getSource(), searchRequest.getDestination());
		List<SearchResponse> searchResponses = new ArrayList<>();
		
		for(Route route: routes) {
			List<Stations> stations = repository.findByTrainId(route.getTrainId());
			List<String> path = new ArrayList<>();
			
			for(Stations station: stations) {
				path.add(station.getStation());
			}
			
            SearchResponse searchResponse = new SearchResponse(route.getTrainId(), searchRequest.getSource(), searchRequest.getDestination(), path, route.getArrivalTime(), route.getDepartureTime());
            searchResponses.add(searchResponse);
		}
		
		return searchResponses;
		
	}
	
	public ResponseEntity<List<Routes>> addRoute(List<Routes> routes){
	    List<Routes> savedRoutes = new ArrayList<>();

		try {
			for (Routes route : routes) {
	            Routes savedRoute = repository.save(route);
	            savedRoutes.add(savedRoute);
	        }
	        return new ResponseEntity<>(savedRoutes, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.tk.seat_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tk.seat_service.model.Train;
import com.tk.seat_service.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	TrainRepository repository;
	
	public ResponseEntity<Train> saveTrain(Train train){
		try {
			repository.save(train);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

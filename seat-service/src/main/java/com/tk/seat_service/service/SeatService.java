package com.tk.seat_service.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tk.seat_service.dto.AddStation;
import com.tk.seat_service.dto.BookingRequest;
import com.tk.seat_service.model.StationToSeatMapping;
import com.tk.seat_service.model.Train;
import com.tk.seat_service.repository.SeatRepository;
import com.tk.seat_service.repository.TrainRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SeatService {
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	TrainRepository trainRepository;
	
	public boolean isSeatAvailable(BookingRequest bookingRequest) {
		
		List<StationToSeatMapping> route = seatRepository.findAllByTrainTrainId(bookingRequest.getTrainId());
		for(StationToSeatMapping staion : route) {
			System.out.println(staion);
		}
		Train train = trainRepository.findByTrainId(bookingRequest.getTrainId());
		
//		if(train==null || route.isEmpty()) {
//			return false;
//		}
		
		int totalSeats = train.getSeatCount();
		
		log.info("Total Seats: "+totalSeats);
		
		Set<Integer> unavailableSeats = new HashSet<>();
		
		boolean track = false;
		
		for(StationToSeatMapping station : route) {
			if(station.getStation().equals(bookingRequest.getSource())) {
				track = true;
			}else if(station.getStation().equals(bookingRequest.getDestination())) {
				break;
			}
			if(track) {
				unavailableSeats.addAll(station.getSeats());
				for (Integer seat : station.getSeats()) {
				    System.out.println(seat);
				}
			}
		}
		

		System.out.println("Hashset");
		for(Integer i: unavailableSeats) {
			System.out.println(i);
		}
		
		int availableSeat = -1;
		for(int i=1; i<=totalSeats; i++) {
			if(!unavailableSeats.contains(i)) {
				availableSeat = i;
//				log.info("Seat available: ", i);
				System.out.println("Seat available: "+i);
				break;
			}
		}
		
		if(availableSeat == -1) {
			log.warn("Seat not available!");
			System.out.println("Seat not available");
			return false;
		}
		
		track = false;
		
		for(StationToSeatMapping station : route) {
			if(station.getStation().equals(bookingRequest.getSource())) {
				track = true;
			}else if(station.getStation().equals(bookingRequest.getDestination())) {
				break;
			}
			if(track) {
				List<Integer> seats = station.getSeats();
				seats.add(availableSeat);
				station.setSeats(seats);
				seatRepository.save(station);
			}
			
		}
		
		return true;
	}

	public ResponseEntity<StationToSeatMapping> saveStation(AddStation station) {
		try {
			Train train = trainRepository.findById(station.getTrainId())
			        .orElseThrow(() -> new RuntimeException("Train not found with ID: " + station.getTrainId()));
			StationToSeatMapping mapping = new StationToSeatMapping();
			mapping.setTrain(train);
			mapping.setStation(station.getStation());
			mapping.setSeats(station.getSeats());
			mapping.setJourneyDate(station.getJourneyDate());
			seatRepository.save(mapping);
			return new ResponseEntity<>(mapping,HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<StationToSeatMapping>> getStations() {
		List<StationToSeatMapping> list = seatRepository.findAll();
		if(list == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
}

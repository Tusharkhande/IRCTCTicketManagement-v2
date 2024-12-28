package com.tk.seat_service.controller;

import com.tk.seat_service.dto.AddStation;
import com.tk.seat_service.dto.BookingRequest;
import com.tk.seat_service.dto.CancelRequest;
import com.tk.seat_service.dto.SeatResponse;
import com.tk.seat_service.model.StationToSeatMapping;
import com.tk.seat_service.service.SeatService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/train/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/available")
    public ResponseEntity<SeatResponse> isSeatAvailable(@RequestBody BookingRequest bookingRequest) {
        return seatService.isSeatAvailable(bookingRequest);
    }
    

    @PostMapping
	public ResponseEntity<StationToSeatMapping> addStation(@RequestBody AddStation station) {
		return seatService.saveStation(station);
	}
    
    @GetMapping
    public ResponseEntity<List<StationToSeatMapping>> getStations(){
    	return seatService.getStations();
    }
    
    @PostMapping("/cancel")
    public ResponseEntity<Boolean> cancelTicket(@RequestBody CancelRequest cancelRequest){
    	return seatService.cancelTicket(cancelRequest);
    }
}
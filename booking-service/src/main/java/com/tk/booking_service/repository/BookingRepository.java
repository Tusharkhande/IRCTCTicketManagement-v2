package com.tk.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tk.booking_service.model.Booking;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	Booking findByBookingId(int bookingId);
	
	List<Booking> findByUserId(int userId);
}

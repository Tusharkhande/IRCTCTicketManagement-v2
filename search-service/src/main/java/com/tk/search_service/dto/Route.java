package com.tk.search_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
	private String trainId;
    private String station;
    private String arrivalTime;
    private String departureTime;
}

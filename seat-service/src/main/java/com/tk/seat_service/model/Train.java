package com.tk.seat_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @Column(name = "train_id")
    private String trainId;
    private String trainName;
    private int seatCount;
}
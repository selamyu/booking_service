package com.example.booking.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resevation {
    private Long id;
    private LocalDateTime reserveDate;
    private LocalDateTime expiryDate;
    private Customer customer;
    private Property property;


    public void checkAndCancelReservation() {
        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(expiryDate))  {
            boolean cancel = true;
            // we can add additional logic here, such as sending a notification.
        }
    }
}

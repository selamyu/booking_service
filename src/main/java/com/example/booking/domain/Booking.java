package com.example.booking.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Currency;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-mm-dd",shape = JsonFormat.Shape.STRING)
    private LocalDateTime checkIn;

    @JsonFormat(pattern = "yyyy-mm-dd",shape = JsonFormat.Shape.STRING)
    private LocalDateTime checkout;
    private double totalPrice;
    private Currency currency;
    @ManyToOne
    private Customer customer;

    private Owner owner;
    @ManyToOne
    private Property property;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}

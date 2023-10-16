package com.example.booking.controller;

import com.example.booking.domain.Booking;
import com.example.booking.exceptionHandler.BookingNotFoundException;
import com.example.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    @Autowired
    private final BookingService bookingService;
    @PostMapping("/create-booking")
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }
//
//    public Booking confirmBooking(Long id){
//        return bookingService.confirmBooking(id);
//    }
    @GetMapping
    public ResponseEntity<List<Booking>> findAllBookings() {
        return ResponseEntity.ok(bookingService.findAllBookings());
    }

    @GetMapping("{id}")

    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }
    @PutMapping({"/{id}"})
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) throws BookingNotFoundException {
        return bookingService.updateBooking(id, booking);
    }


    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) throws BookingNotFoundException{
        return bookingService.cancelBooking(id);
    }
}

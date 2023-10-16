package com.example.booking.service;

import com.example.booking.domain.Booking;
import com.example.booking.exceptionHandler.BookingNotFoundException;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
//    Booking confirmBooking(Long id);

    List<Booking> findAllBookings();

    Booking getBookingById(Long id);

    Booking updateBooking(Long id, Booking booking) throws BookingNotFoundException;

    Booking cancelBooking(Long id) throws BookingNotFoundException;


}

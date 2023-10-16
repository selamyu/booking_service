package com.example.booking.exceptionHandler;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String message){
        super(message);
    }
}

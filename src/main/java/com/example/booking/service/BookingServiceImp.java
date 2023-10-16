package com.example.booking.service;

import com.example.booking.domain.Booking;
import com.example.booking.domain.BookingStatus;
import com.example.booking.exceptionHandler.BookingNotFoundException;
import com.example.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {
    @Autowired
    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(Booking booking) {
        booking.setTotalPrice(calculateTotalPrice(booking));
        booking.setStatus(BookingStatus.PENDING);
        Booking savedBooking = bookingRepository.save(booking);

        // Send booking confirmation message
//        sendBookingConfirmationMSG(savedBooking);

        return savedBooking;
//        return bookingRepository.save(booking);
    }
    public Booking confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null && BookingStatus.PENDING.equals(booking.getStatus())) {
            booking.setStatus(BookingStatus.CONFIRMED);
            return bookingRepository.save(booking);
        } else {
            // Handle the case when the booking cannot be confirmed
            return null;
        }
    }


    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        return bookingRepository.findById(id).map(bk -> {
            bk.setCheckIn(booking.getCheckIn());
            bk.setCheckout(booking.getCheckout());
            bk.setTotalPrice(booking.getTotalPrice());
            bk.setCurrency(booking.getCurrency());
            bk.setCustomer(booking.getCustomer());
            bk.setOwner(booking.getOwner());
            bk.setProperty(booking.getProperty());
            bk.setStatus(booking.getStatus());
            return bookingRepository.save(bk);
        }).orElseThrow(() -> new BookingNotFoundException("sorry, booking not found"));
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return null;
        }
        booking.setStatus(BookingStatus.CANCELED);
        return bookingRepository.save(booking);
    }

    private double calculateTotalPrice(Booking booking){
        String localCurrency = booking.getCurrency().getSymbol();
        double numberOfNights = booking.getCheckout().getSecond() - booking.getCheckIn().getSecond();
        double totalPrice = (booking.getProperty().getPricePerNight()  * numberOfNights * booking.getProperty().getNumGuest());
        return totalPrice;
    }

}

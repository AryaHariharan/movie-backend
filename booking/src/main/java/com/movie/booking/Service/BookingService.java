package com.movie.booking.Service;

import com.movie.booking.Entity.Booking;
import com.movie.booking.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public Booking bookTicket(Booking booking) {
        return repo.save(booking);
    }

    public List<Booking> getUserBookings(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return repo.findAll();
    }
}
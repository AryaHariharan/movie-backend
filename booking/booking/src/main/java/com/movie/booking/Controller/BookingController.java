package com.movie.booking.Controller;

import com.movie.booking.Entity.Booking;
import com.movie.booking.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingRepository repo;

    @PostMapping
    public Booking book(@RequestBody Booking booking) {
        return repo.save(booking);
    }
}
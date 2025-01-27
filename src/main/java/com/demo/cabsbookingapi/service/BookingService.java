package com.demo.cabsbookingapi.service;

import com.demo.cabsbookingapi.entity.Booking;
import com.demo.cabsbookingapi.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private static final List<String> VALID_LOCATIONS = Arrays.asList(
        "Manchester", "Stockport", "Sale", "Trafford", "Salford", "Bury", "Rochdale", "Oldham", "Tameside"
    );

    private static final List<String> VALID_CAB_TYPES = Arrays.asList("Economy", "Premium");

    public Booking createBooking(String source, String destination, String cabType) {
       
        if (!VALID_LOCATIONS.contains(source)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid source location");
        }

        if (!VALID_LOCATIONS.contains(destination)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid destination location");
        }

        if (!VALID_CAB_TYPES.contains(cabType)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid cab type");
        }

        Booking booking = new Booking(source, destination, cabType, LocalDateTime.now());
        return bookingRepository.save(booking);
    }
}

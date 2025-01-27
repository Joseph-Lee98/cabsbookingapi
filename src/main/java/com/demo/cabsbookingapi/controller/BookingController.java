package com.demo.cabsbookingapi.controller;

import com.demo.cabsbookingapi.entity.Booking;
import com.demo.cabsbookingapi.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking.getSource(), booking.getDestination(), booking.getCabType());
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getLocations() {
        List<String> locations = Arrays.asList(
                "Manchester", "Stockport", "Sale", "Trafford", "Salford", "Bury", "Rochdale", "Oldham", "Tameside"
        );
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/cab-types")
    public ResponseEntity<List<String>> getCabTypes() {
        List<String> cabTypes = Arrays.asList("Economy", "Premium");
        return ResponseEntity.ok(cabTypes);
    }
}

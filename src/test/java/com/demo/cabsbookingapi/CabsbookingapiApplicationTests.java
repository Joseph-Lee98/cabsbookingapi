package com.demo.cabsbookingapi;

import com.demo.cabsbookingapi.entity.Booking;
import com.demo.cabsbookingapi.repo.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CabsbookingapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    public void setup() {
        bookingRepository.deleteAll();
    }

    // Test: Successful booking
    @Test
    public void testCreateBooking_Success() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"Oldham\", \"destination\": \"Manchester\", \"cabType\": \"Economy\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.source").value("Oldham"))
                .andExpect(jsonPath("$.destination").value("Manchester"))
                .andExpect(jsonPath("$.cabType").value("Economy"));

        Booking savedBooking = bookingRepository.findAll().get(0);
        assert savedBooking.getSource().equals("Oldham");
        assert savedBooking.getDestination().equals("Manchester");
        assert savedBooking.getCabType().equals("Economy");
    }

    // Test: Booking with empty fields
    @Test
    public void testCreateBooking_Failed_EmptyFields() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"\", \"destination\": \"\", \"cabType\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    // Test: Booking with missing fields
    @Test
    public void testCreateBooking_Failed_MissingFields() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"Oldham\", \"destination\": \"\"}")) 
                .andExpect(status().isBadRequest());
    }

    // Test: Invalid cab type
    @Test
    public void testCreateBooking_Failed_InvalidCabType() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"Oldham\", \"destination\": \"Manchester\", \"cabType\": \"FlyingCar\"}"))
                .andExpect(status().isBadRequest());
    }

    // Test: Invalid source
    @Test
    public void testCreateBooking_Failed_InvalidSource() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"UnknownPlace\", \"destination\": \"Manchester\", \"cabType\": \"Economy\"}"))
                .andExpect(status().isBadRequest());
    }

    // Test: Invalid destination
    @Test
    public void testCreateBooking_Failed_InvalidDestination() throws Exception {
        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"Oldham\", \"destination\": \"UnknownPlace\", \"cabType\": \"Economy\"}"))
                .andExpect(status().isBadRequest());
    }
}

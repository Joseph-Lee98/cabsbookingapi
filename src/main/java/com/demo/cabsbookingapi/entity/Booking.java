package com.demo.cabsbookingapi.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Source cannot be blank")
    private String source;

    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotBlank(message = "Cab type cannot be blank")
    private String cabType;

    private LocalDateTime bookingTime;

    public Booking() {}

    public Booking(String source, String destination, String cabType, LocalDateTime bookingTime) {
        this.source = source;
        this.destination = destination;
        this.cabType = cabType;
        this.bookingTime = bookingTime;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCabType() {
		return cabType;
	}

	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

    
}
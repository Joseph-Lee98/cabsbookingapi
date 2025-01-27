package com.demo.cabsbookingapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.cabsbookingapi.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

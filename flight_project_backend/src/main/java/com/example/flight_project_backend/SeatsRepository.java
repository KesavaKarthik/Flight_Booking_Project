package com.example.flight_project_backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats, SeatsId> {
    Seats findByFlightNumberAndTravelDate(String flightNumber, String travelDate);
}


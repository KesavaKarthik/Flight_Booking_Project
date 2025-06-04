package com.example.flight_project_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository gives us all the CRUD operations for Flight entity
public interface FlightRepository extends JpaRepository<Flight, String> {

    // Custom method to find flights based on user input (e.g., fromLocation, toLocation)
    List<Flight> findByFromLocationAndToLocation(String fromLocation, String toLocation);

    //List<Flight> findAll();

}

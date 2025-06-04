package com.example.flight_project_backend;
import jakarta.persistence.*;


@Entity
@IdClass(SeatsId.class)
@Table(name = "flight_seats")
public class Seats {
    @Id
    @Column(name = "flight_number")
    private String flightNumber;
    
    @Id
    @Column(name = "travel_date")
    private String travelDate;

    @Column(name = "seats_booked")
    private int totalSeats;
    
    public Seats() {
    // Required by JPA
    }
    public Seats(String flightNumber, String travelDate, int totalSeats) {
        this.flightNumber = flightNumber;
        this.travelDate = travelDate;
        this.totalSeats = totalSeats;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;

        
    }
    public void setTravelDate(String TravelDate){
        this.travelDate = TravelDate;

        
    }
    public void setseats(int totalSeats){
        this.totalSeats = totalSeats;

        
    }
    public String getFlightNumber(){
        return flightNumber;

        
    }
    public String getTravelDate(){
        return travelDate;

        
    }
    public int getSeats(){
        return totalSeats;

        
    }
    


    
    
}

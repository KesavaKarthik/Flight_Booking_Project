package com.example.flight_project_backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final SeatsRepository seatsRepository;
    

    public FlightController(FlightRepository flightRepository, BookingRepository bookingRepository ,SeatsRepository seatsRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.seatsRepository = seatsRepository;
        
    }
    // ✅ Get filtered flights
    @PostMapping("/getFlights")
    public List<Flight> getFilteredFlights(@RequestBody Map<String, String > flight_data) {
        System.out.println("Received: " + flight_data);

        String fromLocation = flight_data.get("from");
        String toLocation = flight_data.get("to");
        String date = flight_data.get("date");
        String passengers = flight_data.get("passengers");
        System.out.println(fromLocation + " " + toLocation);

        System.out.println(fromLocation + " " + toLocation);
        List<Flight> flights = flightRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
        List<Flight> final_flights = new java.util.ArrayList<>();
        Seats temp;
        for(int i = 0; i <flights.size() ; i++){
            temp = seatsRepository.findByFlightNumberAndTravelDate(flights.get(i).getFlightNumber(), date);
            if( temp == null){
                final_flights.add(flights.get(i));
            }
            else if(temp.getSeats() + Integer.parseInt(passengers) <= 180){
                final_flights.add(flights.get(i));


            }
            

        }
        
        System.out.println("Found Flights: " + flights.size()); // Log the size of the list
    
    return final_flights;
    }
    @PostMapping("/SeatsPosition")
    public String seatsPosition(@RequestBody Map<String , String> flight){
        String flightNumber = flight.get("flightNumber");
        String travelDate = flight.get("travleDate");
        Seats temp = seatsRepository.findByFlightNumberAndTravelDate(flightNumber , travelDate);
        if( temp != null){
            return temp.getSeatPosition();
        }
        else return "";
    }
    // ✅ Create booking
    
    @PostMapping("/booking")
    public void createBooking(@RequestBody Booking booking) {
        System.out.println("hi");
        bookingRepository.save(booking);
        


    }
    @PostMapping("/seatsOfFlight")
    public void removeSeats(@RequestBody Map<String , String> seatPositions){
        String flightNumber = seatPositions.get("flightNumber");
        String date = seatPositions.get("date");
        String passengersStr = seatPositions.get("passengers");
        String selectedSeats = seatPositions.get("seats");
        System.out.println(selectedSeats + "hi");
        System.out.println(flightNumber + "hi");
        int passengers = Integer.parseInt(passengersStr);
        System.out.println("passengers = " +  passengers);
        Seats response = seatsRepository.findByFlightNumberAndTravelDate(flightNumber, date);
        if( response == null){
            Seats flightseats = new Seats();
            flightseats.setseats(passengers);
            flightseats.setFlightNumber(flightNumber);
            flightseats.setTravelDate(date);
            flightseats.setSeatsPosition(selectedSeats);



            
            seatsRepository.save(flightseats);
            
        }
        else{
            response.setSeatsPosition(selectedSeats);
            response.setseats( response.getSeats() + passengers);
            seatsRepository.save(response);

        }

    }

    // ✅ Login endpoint using instance of LoginRepository

    @Autowired
    private LoginRepository loginRepository;
    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody Login login){
        Login details = loginRepository.findByEmailId(login.getEmailId());
        if(details == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email");

        }
        else if(!details.getPassword().equals(login.getPassword())){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");

        }
        else return ResponseEntity.ok("Login successful");




    }
    // creating object of repository
    @Autowired
    private SignupRepository signupRepository;
    @PostMapping("/signup")
    public Login Signup(@RequestBody Login login){
        System.out.println("hi");
        return signupRepository.save(login);
    }
    
}

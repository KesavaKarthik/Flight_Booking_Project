package com.example.flight_project_backend;

import java.io.Serializable;
import java.util.Objects;

public class SeatsId implements Serializable {
    private String flightNumber;
    private String travelDate;

    public SeatsId() {}

    public SeatsId(String flightNumber, String travelDate) {
        this.flightNumber = flightNumber;
        this.travelDate = travelDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatsId)) return false;
        SeatsId that = (SeatsId) o;
        return Objects.equals(flightNumber, that.flightNumber) &&
               Objects.equals(travelDate, that.travelDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, travelDate);
    }
}

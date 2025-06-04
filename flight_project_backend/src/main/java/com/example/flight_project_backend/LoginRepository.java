package com.example.flight_project_backend;
//import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login , String> {
    Login findByEmailId(String emailId);
}


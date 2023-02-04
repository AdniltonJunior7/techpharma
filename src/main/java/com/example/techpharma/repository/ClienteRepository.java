package com.example.techpharma.repository;

import com.example.techpharma.model.Cliente;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,UUID>{
    
}
package com.example.techpharma.repository;

import com.example.techpharma.model.Mercadoria;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoriaRepository extends JpaRepository<Funcionario,UUID>{
    
}
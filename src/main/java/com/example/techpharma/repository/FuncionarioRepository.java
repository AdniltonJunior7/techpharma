package com.example.techpharma.repository;

import com.example.techpharma.model.Funcionario;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,UUID>{
    
}
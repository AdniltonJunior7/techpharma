package com.example.techpharma.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity

public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 8, max = 256)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 8)
    private Number password;

    @NotNull
    @DateTimeFormat
    private Date DataNascimento;

}
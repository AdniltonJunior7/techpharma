package com.example.techpharma.controller;

import com.example.techpharma.model.Funcionario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.techpharma.repository.FuncionarioRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class FuncionarioController{
    @Autowired
    FuncionarioRepository FuncionarioRepository;

    @GetMapping("/Funcionarios")
    public ResponseEntity<List<Funcionario>> getAllProducts(){
        return new ResponseEntity<>(FuncionarioRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/Funcionarios/{id}")
    public ResponseEntity<Funcionario>
            getOneProduct(@PathVariable(value = "id")UUID id){
        Optional<Funcionario> Funcionario0 = FuncionarioRepository.findById(id);
        if(Funcionario0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(Funcionario0.get(), HttpStatus.OK);

    }

    @PostMapping("/Funcionarios/{id}")
    public ResponseEntity<Funcionario> saveProduct(@RequestBody @Valid Funcionario Funcionario){
        return new ResponseEntity<>(FuncionarioRepository.save(Funcionario), HttpStatus.CREATED);

    }

    @DeleteMapping("/Funcionarios/{id}")
    public ResponseEntity<?>
            deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<Funcionario> product0 = FuncionarioRepository.findById(id);
        if (product0.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }        

            FuncionarioRepository.delete(product0.get());
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/Funcionarios/{id}")
    public ResponseEntity<Funcionario>
            updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid Funcionario Funcionario){
        Optional<Funcionario> Funcionario0 = FuncionarioRepository.findById(id);
        if(Funcionario0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        }
        Funcionario.setId(id);
        return new ResponseEntity<>(FuncionarioRepository.save(Funcionario), HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<Funcionario, String>handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<Funcionario, String> erros = new HashMaps<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.put(fieldName, errorMessage);

        });
        return erros;
    }
}
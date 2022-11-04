package com.example.techpharma.controller;

import com.example.techpharma.model.Cliente;
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
public class ClienteController{
    @Autowired
    ClienteRepository ClienteRepository;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllProducts(){
        return new ResponseEntity<>(ClienteRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/Cliente/{id}")
    public ResponseEntity<Cliente>
            getOneProduct(@PathVariable(value = "id")UUID id){
        Optional<Cliente> Cliente0 = ClienteRepository.findById(id);
        if(Cliente0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(Cliente0.get(), HttpStatus.OK);

    }

    @PostMapping("/CLientes/{id}")
    public ResponseEntity<Cliente> saveProduct(@RequestBody @Valid Cliente CLiente){
        return new ResponseEntity<>(ClienteRepository.save(Cliente), HttpStatus.CREATED);

    }

    @DeleteMapping("/Clientes/{id}")
    public ResponseEntity<?>
            deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<Cliente> product0 = ClienteRepository.findById(id);
        if (product0.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }        

            ClienteRepository.delete(product0.get());
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/Clientes/{id}")
    public ResponseEntity<Cliente>
            updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid Cliente Cliente){
        Optional<Cliente> Cliente0 = ClienteRepository.findById(id);
        if(Cliente0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        }
        Cliente.setId(id);
        return new ResponseEntity<>(ClienteRepository.save(Cliente), HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<Cliente, String>handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<Cliente, String> erros = new HashMaps<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.put(fieldName, errorMessage);

        });
        return erros;
    }
}
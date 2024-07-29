package com.company.sukhachevfinalstep.controller;

import com.company.sukhachevfinalstep.dto.ClientDto;
import com.company.sukhachevfinalstep.service.ClientServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientServiceImpl clientService;
    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCLients(){
        List<ClientDto> clientDtos = clientService.getAllClients();
        if (clientDtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDtos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@Min(value = 1)@PathVariable Long id){
        ClientDto clientDto = clientService.getClientById(id);
        if (clientDto == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(clientDto);
    }
    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientDto clientDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(clientService.saveClient(clientDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@Min(value = 1)@PathVariable Long id
            ,@Valid @RequestBody ClientDto clientDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clientService.updateClient(id,clientDto));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@Min(value = 1)@PathVariable Long id){
        if (clientService.getClientById(id) == null){
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}

package com.assisjrs.crudjava.controller;

import com.assisjrs.crudjava.model.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClienteService service;

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponse> get(@PathVariable final Long id){
        return ResponseEntity.ok(mapper.map(service.byId(id), ClienteResponse.class));
    }
}

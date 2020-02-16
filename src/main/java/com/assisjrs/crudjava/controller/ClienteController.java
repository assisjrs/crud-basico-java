package com.assisjrs.crudjava.controller;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable final Long id, @RequestBody final ClienteRequest request){
        final Cliente cliente = mapper.map(request, Cliente.class);
        cliente.setId(id);

        service.updateBy(cliente);
        return ResponseEntity.noContent()
                             .build();
    }
}

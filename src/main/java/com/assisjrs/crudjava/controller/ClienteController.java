package com.assisjrs.crudjava.controller;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> busca(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome){

        final Page<Cliente> clientesPage = service.busca(page, size, cpf, nome);

        final List<ClienteResponse> responses = new ArrayList<>(clientesPage.getSize());

        clientesPage.forEach(c -> responses.add(mapper.map(c, ClienteResponse.class)));

        return ResponseEntity.ok(new PageImpl<>(responses, clientesPage.getPageable(), clientesPage.getTotalElements()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable final Long id, @RequestBody final ClienteRequest request){
        return atualizar(id, request);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> patch(@PathVariable final Long id, @RequestBody final ClienteRequest request){
        return atualizar(id, request);
    }

    private ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody final ClienteRequest request){
        final Cliente cliente = mapper.map(request, Cliente.class);
        cliente.setId(id);

        service.updateBy(cliente);
        return ResponseEntity.noContent()
                .build();
    }
}

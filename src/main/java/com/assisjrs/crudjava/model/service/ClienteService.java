package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.repository.ClienteRepository;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente byId(final Long id) {
        return repository.findById(id)
                         .orElseThrow(ClienteNaoEncontradoException::new);
    }

    public void deleteById(Long id) {
        final Cliente cliente = byId(id);

        repository.delete(cliente);
    }
}

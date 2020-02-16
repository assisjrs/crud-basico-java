package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    public Cliente byId(final Long id) {
        if(id != 1)
            throw new ClienteNaoEncontradoException();

        final Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNome("Assis Júnior");
        cliente.setCpf("000000000000");
        cliente.setNascimento(LocalDate.of(1981, 11, 14));

        return cliente;
    }
}

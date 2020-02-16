package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.repository.ClienteRepository;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import com.assisjrs.crudjava.model.service.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

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

    public Cliente updateBy(final Cliente cliente) {
        final Cliente clienteRecuperado = byId(cliente.getId());

        try {
            copyProperties(clienteRecuperado, cliente);
        } catch (Exception e) {
            throw new SystemException();
        }

        repository.save(clienteRecuperado);

        return clienteRecuperado;
    }
}

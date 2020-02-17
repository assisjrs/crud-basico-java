package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.repository.ClienteRepository;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Page<Cliente> busca(final int page, final int size, final String cpf, final String nome) {
        final PageRequest pageRequest = PageRequest.of(page, size, ASC,"nome");

        return repository.busca(pageRequest, cpf, isNotNullOrEmpty(nome)? nome.toLowerCase(): null);
    }

    public Cliente byId(final Long id) {
        return repository.findById(id)
                         .orElseThrow(ClienteNaoEncontradoException::new);
    }

    public void deleteById(Long id) {
        final Cliente cliente = byId(id);

        repository.delete(cliente);
    }

    public void updateBy(final Cliente cliente) {
        final Cliente clienteRecuperado = byId(cliente.getId());

        if(isNotNullOrEmpty(cliente.getNome()))
            clienteRecuperado.setNome(cliente.getNome());

        if(isNotNullOrEmpty(cliente.getCpf()))
            clienteRecuperado.setCpf(cliente.getCpf());

        if(cliente.getNascimento() != null)
            clienteRecuperado.setNascimento(cliente.getNascimento());

        repository.save(clienteRecuperado);
    }

    private static boolean isNotNullOrEmpty(final String s){
        return s != null && !"".equals(s.trim());
    }

    public Cliente save(final Cliente cliente) {
        return repository.save(cliente);
    }
}

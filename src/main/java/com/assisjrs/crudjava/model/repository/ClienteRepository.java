package com.assisjrs.crudjava.model.repository;

import com.assisjrs.crudjava.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    @Query("FROM Cliente c WHERE" +
            " ((:cpf IS NOT NULL AND c.cpf = :cpf)" +
            " OR (:nome IS NOT NULL AND LOWER(c.nome) like %:nome%))" +
            " OR (:cpf IS NULL AND :nome IS NULL)")
    Page<Cliente> busca(Pageable pageable, @Param("cpf") String cpf, @Param("nome") String nome);
}

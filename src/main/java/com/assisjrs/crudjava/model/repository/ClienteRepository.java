package com.assisjrs.crudjava.model.repository;

import com.assisjrs.crudjava.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {}

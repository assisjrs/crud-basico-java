package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.repository.ClienteRepository;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/ExcluirClienteServiceIntegrationTest.xml")
})
public class ExcluirClienteServiceIntegrationTest {

	@Autowired
	private ClienteService service;

	@Autowired
	private ClienteRepository repository;

	@Test
	void deve_excluir_o_cliente_pelo_id() {
		service.deleteById(1L);

		final Optional<Cliente> cliente = repository.findById(1L);

		assertThat(cliente.isPresent()).isFalse();
	}

	@Test
	void deve_retornar_cliente_nao_encontrado_exception_quando_o_cliente_nao_existir() {
		assertThrows(ClienteNaoEncontradoException.class, () -> service.deleteById(-1L));
	}
}
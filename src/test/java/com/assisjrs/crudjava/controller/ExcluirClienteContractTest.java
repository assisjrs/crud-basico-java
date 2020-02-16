package com.assisjrs.crudjava.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureWebTestClient
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/ExcluirClienteContractTest.xml")
})
public class ExcluirClienteContractTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void deve_retornar_204_quando_o_cliente_existir() {
		webClient
				.delete().uri("/clientes/1")
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	void deve_retornar_404_quando_o_cliente_nao_existir() {
		webClient
				.delete().uri("/clientes/2")
				.exchange()
				.expectStatus().isNotFound();
	}
}
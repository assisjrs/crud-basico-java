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

import java.time.LocalDate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureWebTestClient
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/AtualizacaoParcialClienteContractTest.xml")
})
public class AtualizacaoParcialClienteContractTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void deve_salvar_204_quando_atualizar_o_cliente() {
		final ClienteRequest request = new ClienteRequest();

		request.setCpf("88888888888");
		request.setNome("F. Assis M. Junior");
		request.setNascimento(LocalDate.now());

		webClient
				.patch().uri("/clientes/1").bodyValue(request)
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	void deve_salvar_404_quando_o_cliente_nao_existir() {
		final ClienteRequest request = new ClienteRequest();

		webClient
				.patch().uri("/clientes/2").bodyValue(request)
				.exchange()
				.expectStatus().isNotFound();
	}
}
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureWebTestClient
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/AtualizacaoClienteContractTest.xml")
})
public class InserirClienteContractTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void ao_salvar_deve_retornar_201() {
		final ClienteRequest request = new ClienteRequest();

		request.setCpf("12121212121");
		request.setNome("Albert Einstein");
		request.setNascimento(LocalDate.of(1879, 3, 14));

		webClient
				.post().uri("/clientes").bodyValue(request)
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	void deve_retornar_o_id_do_cliente() {
		final ClienteRequest request = new ClienteRequest();

		request.setCpf("23232323232");
		request.setNome("Michael Faraday");
		request.setNascimento(LocalDate.of(1791, 9, 22));

		final ClienteResponse output = webClient
				.post().uri("/clientes").bodyValue(request)
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getId()).isNotNull();
	}
}
package com.assisjrs.crudjava.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureWebTestClient
public class ExibirClienteContractTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void deve_retornar_200_quando_o_cliente_existir() {
		webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void deve_retornar_404_quando_o_cliente_nao_existir() {
		webClient
				.get().uri("/clientes/2")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void deve_retornar_o_id_do_cliente() {
		final ClienteResponse output = webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getId()).isNotNull();
	}

	@Test
	void deve_retornar_o_nome_do_cliente() {
		final ClienteResponse output = webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getNome()).isNotEmpty();
	}

	@Test
	void deve_retornar_o_cpf_do_cliente() {
		final ClienteResponse output = webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getCpf()).isNotEmpty();
	}

	@Test
	void deve_retornar_a_data_nascimento_do_cliente() {
		final ClienteResponse output = webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getNascimento()).isNotNull();
	}

	@Test
	void deve_retornar_a_idade_do_cliente() {
		final ClienteResponse output = webClient
				.get().uri("/clientes/1")
				.exchange()
				.expectBody(ClienteResponse.class).returnResult().getResponseBody();

		assertThat(output.getIdade()).isNotNull();
	}
}
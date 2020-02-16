package com.assisjrs.crudjava.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteResponseTest {

	@Test
	void se_o_nascimento_for_nulo_retornar_a_idade_vazia() {
		final ClienteResponse response = new ClienteResponse();

		response.setNascimento(null);

		assertThat(response.getIdade()).isEqualTo("");
	}

	@Test
	void deve_retornar_a_idade_pela_data_de_nascimento() {
		final ClienteResponse response = new ClienteResponse();

		response.setNascimento(LocalDate.of(1981, 11, 14));

		assertThat(response.getIdade()).isEqualTo("38");
	}
}
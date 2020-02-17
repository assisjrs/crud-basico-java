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
		@DatabaseSetup("/datasets/cliente/ListarClienteContractTest.xml")
})
public class ListarClienteContractTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void deve_retornar_200_quando_houver_dados() {
		webClient
				.get().uri("/clientes")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void caso_nao_seja_informado_nenhuma_paginacao_retornar_pagina_de_10_itens() {
		webClient
				.get().uri("/clientes")
				.exchange()
				.expectBody().jsonPath("$.size").isEqualTo(10);
	}

	@Test
	void se_o_tamanho_da_pagina_for_2_itens_quando_e_exibida_a_segunda_pagina_entao_o_primeiro_item_retornado_e_o_item_3() {
		webClient
				.get().uri("/clientes?page=1&size=2&nome=a")
				.exchange()
				.expectBody().jsonPath("$.content[0].id").isEqualTo(2L);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_nome() {
		webClient
				.get().uri("/clientes?nome=Assis")
				.exchange()
				.expectBody().jsonPath("$.numberOfElements").isEqualTo(1);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_cpf() {
		webClient
				.get().uri("/clientes?cpf=11111111111")
				.exchange()
				.expectBody().jsonPath("$.numberOfElements").isEqualTo(1);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_cpf_e_nome() {
		webClient
				.get().uri("/clientes?nome=Milton&cpf=98989898989")
				.exchange()
				.expectBody().jsonPath("$.numberOfElements").isEqualTo(1);
	}
}
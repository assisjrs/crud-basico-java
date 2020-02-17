package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/ListarClienteServiceIntegrationTest.xml")
})
public class ListarClienteServiceIntegrationTest {

	@Autowired
	private ClienteService service;

	@Test
	void se_o_tamanho_da_pagina_for_2_itens_quando_e_exibida_a_segunda_pagina_entao_o_primeiro_item_retornado_e_o_item_3() {
		final Page<Cliente> clientes = service.busca(1, 2, null, null);

		assertThat(clientes.getContent().get(0).getId()).isEqualTo(2L);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_nome() {
		final Page<Cliente> clientes = service.busca(0, 10, null, "A");

		assertThat(clientes.getTotalElements()).isEqualTo(1);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_cpf() {
		final Page<Cliente> clientes = service.busca(0, 1, "11111111111", null);

		assertThat(clientes.getTotalElements()).isEqualTo(1);
	}

	@Test
	void deve_ser_capaz_de_filtrar_os_dados_por_cpf_e_nome() {
		final Page<Cliente> clientes = service.busca(0, 1, "98989898989", "Milton Friedman");

		assertThat(clientes.getTotalElements()).isEqualTo(1);
	}

}
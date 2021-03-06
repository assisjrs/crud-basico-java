package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.assertj.db.type.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
		@DatabaseSetup("/datasets/clean_database.xml"),
		@DatabaseSetup("/datasets/cliente/AtualizacaoClienteServiceIntegrationTest.xml")
})
public class AtualizacaoParcialClienteServiceIntegrationTest {

	@Autowired
	private ClienteService service;

	@Autowired
	private DataSource dataSource;

	private Request clienteTable;

	@BeforeEach
	void setup() {
		clienteTable = new Request(dataSource, "select nome, cpf, nascimento from cliente where id = ?");
		clienteTable.setParameters(1L);
	}

	@Test
	void deve_salvar_o_nome_alterado() {
		final String nome = "F. Assis Junior";

		final Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome(nome);

		service.updateBy(cliente);

		assertThat(clienteTable).column("nome")
				.value().isEqualTo(nome);
	}

	@Test
	void deve_salvar_o_cpf_alterado() {
		final String cpf = "99999999999";

		final Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setCpf(cpf);

		service.updateBy(cliente);

		assertThat(clienteTable).column("cpf")
				.value().isEqualTo(cpf);
	}

	@Test
	void deve_salvar_a_data_nascimento_alterada() {
		final LocalDate nascimento = LocalDate.of(2020, 2, 16);

		final Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNascimento(nascimento);

		service.updateBy(cliente);

		assertThat(clienteTable).column("nascimento")
				.value().isEqualTo("2020-02-16");
	}
}
package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.time.LocalDate;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/datasets/clean_database.xml")
public class InserirClienteServiceIntegrationTest {

	@Autowired
	private ClienteService service;

	@Autowired
	private DataSource dataSource;

	@Test
	void deve_salvar_o_cliente_no_banco() {
		final Cliente cliente = new Cliente();

		cliente.setCpf("23232323232");
		cliente.setNome("Michael Faraday");
		cliente.setNascimento(LocalDate.of(1791, 9, 22));

		service.save(cliente);

		final Request table = new Request(dataSource, "select count(*) from cliente");
		Assertions.assertThat(table).column(0)
				.value().isNotZero();
	}

	@Test
	@DatabaseSetup("/datasets/clean_database.xml")
	void deve_inserir_o_id_automaticamente() {
		final Cliente cliente = new Cliente();

		cliente.setCpf("34343434343");
		cliente.setNome("Hans Christian Ørsted");
		cliente.setNascimento(LocalDate.of(1777, 3, 14));

		service.save(cliente);

		final Request table = new Request(dataSource, "select id from cliente");
		Assertions.assertThat(table).hasNumberOfRows(1);
	}
}
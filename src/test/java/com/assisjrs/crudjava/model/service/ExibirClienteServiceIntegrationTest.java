package com.assisjrs.crudjava.model.service;

import com.assisjrs.crudjava.model.entity.Cliente;
import com.assisjrs.crudjava.model.service.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ExibirClienteServiceIntegrationTest {

	@Autowired
	private ClienteService service;

	@Test
	void deve_retornar_o_cliente_pelo_id() {
		Cliente cliente = service.byId(1L);

		assertThat(cliente).isNotNull();
	}

	@Test
	void deve_retornar_cliente_nao_encontrado_exception_quando_o_cliente_nao_existir() {
		assertThrows(ClienteNaoEncontradoException.class, () -> service.byId(-1L));
	}
}
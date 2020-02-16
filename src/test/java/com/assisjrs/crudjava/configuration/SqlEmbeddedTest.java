package com.assisjrs.crudjava.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SqlEmbeddedTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	void deve_iniciar_o_sql_embedded() {
		final Query query = entityManager.createNativeQuery("SELECT count(*) FROM   INFORMATION_SCHEMA.SYSTEM_TABLES;");

		assertThat(query.getSingleResult()).isNotNull();
	}
}
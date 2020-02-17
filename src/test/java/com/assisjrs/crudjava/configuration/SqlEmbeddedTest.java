package com.assisjrs.crudjava.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class SqlEmbeddedTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private JdbcTemplate template;

	@Test
	void deve_iniciar_o_sql_embedded() {
		final Query query = entityManager.createNativeQuery("SELECT count(*) FROM INFORMATION_SCHEMA.SYSTEM_TABLES;");

		assertThat(query.getSingleResult()).isNotNull();
	}

	@Test
	public void deve_carregar_flyway() {
		assertThat(template.queryForObject("select count(*) > -1 from \"flyway_schema_history\";", Boolean.class), is(true));
	}
}
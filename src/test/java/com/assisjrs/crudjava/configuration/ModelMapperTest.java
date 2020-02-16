package com.assisjrs.crudjava.configuration;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ModelMapperTest {

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void deve_iniciar_o_model_mapper() {
		assertThat(modelMapper).isNotNull();
	}
}
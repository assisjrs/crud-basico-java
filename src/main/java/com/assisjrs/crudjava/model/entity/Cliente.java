package com.assisjrs.crudjava.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    private Long id;

    @NotEmpty
    @Length(max = 255)
    private String nome;

    @NotEmpty
    @Length(max = 11, min = 11)
    private String cpf;

    @NotNull
    private LocalDate nascimento;
}

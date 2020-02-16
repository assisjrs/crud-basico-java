package com.assisjrs.crudjava.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate nascimento;

    public String getIdade(){
        if(nascimento == null) return "";

        return Long.toString(ChronoUnit.YEARS.between(nascimento, LocalDate.now()));
    }
}

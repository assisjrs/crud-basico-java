package com.assisjrs.crudjava.controller;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteRequest {
    private String nome;
    private String cpf;
    private LocalDate nascimento;
}

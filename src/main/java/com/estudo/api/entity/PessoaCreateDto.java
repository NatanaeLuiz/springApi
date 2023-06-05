package com.estudo.api.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PessoaCreateDto {

    @NotEmpty(message = "O campo NOME precisa esta preenchido")
    private String nome;
    private int idade;
}

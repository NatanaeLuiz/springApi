package com.estudo.api.service;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaDto salvarPessoa(PessoaDto pessoaDto);

    Optional<Pessoa> retornarUmaPessoa(Long id);

    List<Pessoa> retornarTodos();

    void removeUmaPessoa(Long id);

    PessoaDto atualizaUmaPessoa(PessoaDto pessoaDto);
}

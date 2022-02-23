package com.estudo.api.parse;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;
import org.springframework.stereotype.Component;

@Component
public class PessoaDtoParaPessoa {

    public Pessoa parse(PessoaDto pessoaDto) {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDto.getId());
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setIdade(pessoaDto.getIdade());

        return pessoa;
    }
}

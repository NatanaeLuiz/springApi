package com.estudo.api.parse;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;
import org.springframework.stereotype.Component;

@Component
public class PessoaParaPessoaDto {

    public PessoaDto parse(Pessoa pessoa) {

        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setIdade(pessoa.getIdade());

        return pessoaDto;
    }
}

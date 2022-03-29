package com.estudo.api.service;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;
import com.estudo.api.parse.PessoaDtoParaPessoa;
import com.estudo.api.parse.PessoaParaPessoaDto;
import com.estudo.api.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService{

    private final PessoaDtoParaPessoa pessoaDtoParaPessoa;
    private final PessoaRepository pessoaRepository;
    private final PessoaParaPessoaDto pessoaParaPessoaDto;

    @Override
    public PessoaDto salvarPessoa(PessoaDto pessoaDto) {

        Pessoa pessoa = pessoaDtoParaPessoa.parse(pessoaDto);//Pessoa sem id

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);//Pessoa salva

        PessoaDto pessoaDtoTransformado = pessoaParaPessoaDto.parse(pessoaSalva);//Retorna Pessoa Com Id
        return pessoaDtoTransformado;
    }

    @Override
    public Optional<Pessoa> retornarUmaPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> retornarTodos() {
        return pessoaRepository.findAll();
    }

    @Override
    public void removeUmaPessoa(Long id) {

        //Pessoa pessoa = pessoaDtoParaPessoa.parse(id);//Pessoa sem id

        pessoaRepository.deleteById(id);

        //PessoaDto pessoaDtoTransformado = pessoaParaPessoaDto.parse(pessoaSalva);//Retorna Pessoa Com Id
       // return pessoaDtoTransformado;
    }

    @Override
    public PessoaDto atualizaUmaPessoa(PessoaDto pessoaDto) {

        Pessoa pessoa = pessoaDtoParaPessoa.parse(pessoaDto);

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        PessoaDto pessoaDtoTransformado = pessoaParaPessoaDto.parse(pessoaSalva);//Retorna Pessoa Com Id
        return pessoaDtoTransformado;
    }

}

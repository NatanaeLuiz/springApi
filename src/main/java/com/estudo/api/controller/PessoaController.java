package com.estudo.api.controller;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;
import com.estudo.api.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor //construtor da pessoaService
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping(value = "create")
    public ResponseEntity<PessoaDto> salvaPessoa(@RequestBody PessoaDto pessoaDto) {
        PessoaDto p = pessoaService.salvarPessoa(pessoaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> retornaUmaPessoaPeloId(@PathVariable("id") Long id){
        return pessoaService.retornarUmaPessoa(id);
    }
}

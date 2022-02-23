package com.estudo.api.controller;

import com.estudo.api.entity.PessoaDto;
import com.estudo.api.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}

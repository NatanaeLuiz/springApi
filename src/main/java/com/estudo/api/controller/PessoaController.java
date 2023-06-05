package com.estudo.api.controller;

import com.estudo.api.entity.Pessoa;
import com.estudo.api.entity.PessoaDto;
import com.estudo.api.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor //construtor da pessoaService
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @ApiOperation("Método para criação")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public ResponseEntity<?> salvaPessoa(@Valid @RequestBody PessoaDto pessoaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validaBody(bindingResult);
        }

        PessoaDto p = pessoaService.salvarPessoa(pessoaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @ApiOperation("Método para criação")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create2")
    public ResponseEntity<?> salvaPessoa2(@Valid @RequestBody Pessoa pessoa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validaBody(bindingResult);
        }

        return ResponseEntity.ok(pessoaService.salvarPessoa2(pessoa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retornaUmaPessoaPeloId(@PathVariable("id") Long id){
        Optional<Pessoa> pessoa = pessoaService.retornarUmaPessoa(id);

        if (!pessoa.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa Não encontrada.");

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.retornarUmaPessoa(id));
    }

    @GetMapping("/all")
    public List<Pessoa> retornaTodasPessoas(){
        return pessoaService.retornarTodos();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeUmaPessoaPeloId(@PathVariable("id") Long id){
        Optional<Pessoa> pessoa = pessoaService.retornarUmaPessoa(id);

        if (!pessoa.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa Não encontrada.");

        pessoaService.removeUmaPessoa(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa removida com sucesso!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> atualizaUmaPessoaPeloId(@PathVariable("id") Long id,
                                                          @RequestBody PessoaDto pessoaDto){
        Optional<Pessoa> pessoa = pessoaService.retornarUmaPessoa(id);

        if (!pessoa.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa Não encontrada.");

        pessoaService.atualizaUmaPessoa(pessoaDto);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa atualizada com sucesso!");
    }

    private ResponseEntity<Map<String, String>> validaBody(BindingResult bindingResult) {
        Map<String, String> mapErros = new HashMap<>();
        bindingResult.getFieldErrors().forEach(erro -> {
            mapErros.put(erro.getField(), "Campo: " + erro.getField() + " " + erro.getDefaultMessage());
        }); //Lambda

        return ResponseEntity.badRequest().body(mapErros);
    }
}

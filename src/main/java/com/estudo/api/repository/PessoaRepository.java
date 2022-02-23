package com.estudo.api.repository;

import com.estudo.api.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}

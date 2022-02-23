package com.estudo.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private int idade;


}

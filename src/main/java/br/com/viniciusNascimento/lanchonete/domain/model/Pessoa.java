package br.com.viniciusNascimento.lanchonete.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
 @Column(length = 50, nullable = false)
 protected String nome;
 @EqualsAndHashCode.Include
 @Column(length = 11)
 protected String cpf;
 @Column(length = 13)
 protected String telefone;
 @Column(length = 40)
 protected String email;
 protected LocalDate dataNascimento;

 /*protected String nome;
 @EqualsAndHashCode.Include
 protected String cpf;
 protected String telefone;
 protected String email;
 protected LocalDate dataNascimento;*/

   /* @Id
    protected Long id;
    @Column(name="nom_pessoa")
    protected String nome;
    @Column(name="num_idade")
    protected Integer idade;*/

   /* @Column(length = 50, nullable = false)
    protected String nome;
    @Column(length = 11)
    protected String cpf;
    @Column(length = 13)
    protected String telefone;
    @Column(length = 40)
    protected String email;*/

}

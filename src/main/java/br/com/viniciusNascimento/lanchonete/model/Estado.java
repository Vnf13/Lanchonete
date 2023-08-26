package br.com.viniciusNascimento.lanchonete.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(length = 2, nullable = false)
    protected String uf;
    @Column(length = 40)
    protected String nome;

}

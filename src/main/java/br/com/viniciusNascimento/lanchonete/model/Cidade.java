package br.com.viniciusNascimento.lanchonete.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(length = 40)
    protected String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;
}

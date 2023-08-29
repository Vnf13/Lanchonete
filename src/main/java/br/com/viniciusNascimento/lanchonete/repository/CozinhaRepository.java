package br.com.viniciusNascimento.lanchonete.repository;


import br.com.viniciusNascimento.lanchonete.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> findAll();
    Cozinha findById(Long id);
    Cozinha save(Cozinha cozinha);
    void deleteById(Long id);
}

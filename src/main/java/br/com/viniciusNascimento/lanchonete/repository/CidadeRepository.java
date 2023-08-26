package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> findAll();
    Cidade findById(Long id);
    Cidade save(Cidade cidade);
    void deleteById(Long id);
}

package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.model.Cozinha;

import java.util.List;

public interface ClienteRepository {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);

    /*
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);*/
}

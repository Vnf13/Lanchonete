package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);
}

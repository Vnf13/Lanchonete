package br.com.viniciusNascimento.lanchonete.service;

import br.com.viniciusNascimento.lanchonete.exception.EntidadeEmUsoException;
import br.com.viniciusNascimento.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {
    @Autowired
    private ClienteRepository  clienteRepository;
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public void remover(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" + "de cliente com código %d", clienteId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Esse cadastro %d esta em uso", clienteId));
        }
    }

}

package br.com.viniciusNascimento.lanchonete.service;

import br.com.viniciusNascimento.lanchonete.exception.EntidadeEmUsoException;
import br.com.viniciusNascimento.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.viniciusNascimento.lanchonete.model.Cozinha;
import br.com.viniciusNascimento.lanchonete.model.Estado;
import br.com.viniciusNascimento.lanchonete.repository.CozinhaRepository;
import br.com.viniciusNascimento.lanchonete.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }
    /*public void remover(Long estadoId) {
        estadoRepository.deleteById(estadoId);
    }*/
    public void remover(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" + "de cozinha com código %d", cozinhaId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Esse cadastro %d esta em uso", cozinhaId));
        }
    }

}

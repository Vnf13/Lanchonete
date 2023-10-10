package br.com.viniciusNascimento.lanchonete.service;

import br.com.viniciusNascimento.lanchonete.exception.EntidadeEmUsoException;
import br.com.viniciusNascimento.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.viniciusNascimento.lanchonete.domain.model.Cidade;
import br.com.viniciusNascimento.lanchonete.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

    @Service
    public class CadastroCidadeService {
        @Autowired
        private CidadeRepository cidadeRepository;
        public Cidade salvar(Cidade cidade) {
            return cidadeRepository.save(cidade);
        }

        public void remover(Long cidadeId) {
            try {
                cidadeRepository.deleteById(cidadeId);
            } catch (EmptyResultDataAccessException e) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro" + "de cidade com código %d", cidadeId));
            }catch (DataIntegrityViolationException e){
                throw new EntidadeEmUsoException(
                        String.format("Esse cadastro %d esta em uso", cidadeId));
            }
        }
}

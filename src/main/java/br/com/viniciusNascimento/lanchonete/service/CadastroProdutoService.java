package br.com.viniciusNascimento.lanchonete.service;

import br.com.viniciusNascimento.lanchonete.exception.EntidadeEmUsoException;
import br.com.viniciusNascimento.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.viniciusNascimento.lanchonete.domain.model.Produto;
import br.com.viniciusNascimento.lanchonete.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(Long produtoID) {
        try {
            produtoRepository.deleteById(produtoID);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" + "de produto com código %d", produtoID));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Esse cadastro %d esta em uso", produtoID));
        }
    }
}

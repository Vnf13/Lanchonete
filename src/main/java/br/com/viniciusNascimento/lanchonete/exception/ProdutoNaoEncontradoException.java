package br.com.viniciusNascimento.lanchonete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade não encontrada")

    public class ProdutoNaoEncontradaException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ProdutoNaoEncontradaException(String mensagem) {
            super(mensagem);
        }

    }


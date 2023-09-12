package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.exception.ProdutoNaoEncontradoException;
import br.com.viniciusNascimento.lanchonete.model.Produto;
import br.com.viniciusNascimento.lanchonete.repository.ProdutoRepository;
import br.com.viniciusNascimento.lanchonete.service.CadastroProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private CadastroProdutoService cadastroProdutoService;
    @Autowired
    private ProdutoRepository produtoRepository;
    @GetMapping
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
    @GetMapping("/{produtoId}")
    public Produto buscar(@PathVariable Long produtoId){
        return produtoRepository.findById(produtoId)
                .orElseThrow(()
                        -> new ProdutoNaoEncontradoException
                        ("Produto não encontrado"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar(@RequestBody Produto produto) {
        return cadastroProdutoService.salvar(produto);
    }
    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long produtoId){
        cadastroProdutoService.remover(produtoId);
    }
    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto>
    atualizar(@PathVariable Long produtoId,
              @RequestBody Optional<Produto> produto){
        Optional<Produto> produtoAtual
                = produtoRepository.findById(produtoId);
        if(produtoAtual.isPresent()){
            BeanUtils.copyProperties
                    (produto, produtoAtual.get(), "id");
            Produto produtoSalvo
                    = cadastroProdutoService.salvar(produtoAtual.get());
            return ResponseEntity.ok(produtoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizarParcial
            (@PathVariable Long produtoId,
             @RequestBody Map<String, Object> campos) {
        Optional<Produto> produtoAtual
                = produtoRepository.findById(produtoId);
        if (produtoAtual == null) {
            return ResponseEntity.notFound().build();
        }
        merge(campos,  produtoAtual);
        return atualizar(produtoId, produtoAtual);
    }
    private void merge(Map<String, Object> dadosOrigem,
                       Optional<Produto> produtoDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Produto produtoOrigem =
                objectMapper.convertValue(dadosOrigem,
                        Produto.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {Field field = ReflectionUtils.findField(
                Produto.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor =
                    ReflectionUtils.getField(field, produtoOrigem);
            ReflectionUtils.setField(field,
                    produtoDestino, novoValor);
        });
    }
}
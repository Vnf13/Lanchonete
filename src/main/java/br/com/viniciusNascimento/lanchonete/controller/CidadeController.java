package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.model.Cidade;
import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.model.Cozinha;
import br.com.viniciusNascimento.lanchonete.repository.CidadeRepositoryImpl;
import br.com.viniciusNascimento.lanchonete.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/cidades")
    public class CidadeController {
        @Autowired
        private CadastroCidadeService cadastroCidadeService;
        @Autowired
        private CidadeRepositoryImpl cidadeRepository;
        @GetMapping
        public List<Cidade> listar(){
            return cidadeRepository.findAll();
        }
        @GetMapping("/{cidadeId}")
        public Cidade buscar(@PathVariable Long cidadeId){
            return cidadeRepository.findById(cidadeId);
        }
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Cidade adicionar(@RequestBody Cidade cidade) {
            return cadastroCidadeService.salvar(cidade);
        }
        @DeleteMapping("/{cidadeId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void remover (@PathVariable Long cidadeId){
            cadastroCidadeService.remover(cidadeId);
        }

        @PutMapping("/{cidadeId}")
        public ResponseEntity<Cidade> atualizar
                (@PathVariable Long cidadeId,
                 @RequestBody Cidade cidade){
            Cidade cidadeAtual = cidadeRepository.findById(cidadeId);
            if(cidadeAtual != null){
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");
                Cidade cidadeSalva = cadastroCidadeService.salvar(cidadeAtual);
                return ResponseEntity.ok(cidadeSalva);
            }
            return ResponseEntity.notFound().build();
        }
}

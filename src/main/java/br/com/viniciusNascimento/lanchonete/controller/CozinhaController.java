package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.domain.model.Cozinha;
import br.com.viniciusNascimento.lanchonete.repository.CozinhaRepositoryImpl;
import br.com.viniciusNascimento.lanchonete.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;
    @Autowired
    private CozinhaRepositoryImpl cozinhaRepository;
    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }
    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long cozinhaId){
        cadastroCozinhaService.remover(cozinhaId);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar
            (@PathVariable Long cozinhaId,
             @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId);
        if(cozinhaAtual != null){
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaSalva);
        }
        return ResponseEntity.notFound().build();
    }

}

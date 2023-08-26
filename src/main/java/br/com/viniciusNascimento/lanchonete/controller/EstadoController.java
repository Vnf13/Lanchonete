package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.model.Estado;
import br.com.viniciusNascimento.lanchonete.repository.EstadoRepositoryImpl;
import br.com.viniciusNascimento.lanchonete.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private CadastroEstadoService cadastroEstadoService;
    @Autowired
    private EstadoRepositoryImpl estadoRepository;
    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId){
        return estadoRepository.findById(estadoId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long estadoId){
        cadastroEstadoService.remover(estadoId);
    }


}

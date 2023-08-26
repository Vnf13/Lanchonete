package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.model.Estado;
import br.com.viniciusNascimento.lanchonete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
   @Autowired //Injeção de dependecias
    private ClienteRepository clienteRepository;
    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.listar();
    }

    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.buscar(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteRepository.salvar(cliente);
    }
    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long clienteId){
        clienteRepository.remover(buscar(clienteId));
    }

}

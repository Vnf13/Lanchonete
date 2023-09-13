package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.model.Cidade;
import br.com.viniciusNascimento.lanchonete.model.Cliente;
import br.com.viniciusNascimento.lanchonete.repository.ClienteRepositoryImpl;
import br.com.viniciusNascimento.lanchonete.service.CadastroClienteService;
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

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private CadastroClienteService cadastroClienteService;
    @Autowired
    private ClienteRepositoryImpl clienteRepository;
    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente clienteId) {
        return cadastroClienteService.salvar(clienteId);
    }
    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long clienteId){
        cadastroClienteService.remover(clienteId);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar
            (@PathVariable Long clienteId,
             @RequestBody Cliente cliente){
        Cliente clienteAtual = clienteRepository.findById(clienteId);
        if(clienteAtual != null){
            BeanUtils.copyProperties(cliente, clienteAtual, "id");
            Cliente clienteSalva = cadastroClienteService.salvar(clienteAtual);
            return ResponseEntity.ok(clienteSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<?> atualizarParcial
            (@PathVariable Long clienteId,
             @RequestBody Map<String, Object> campos) {
        Cliente clienteAtual = clienteRepository.findById(clienteId);
        if (clienteAtual == null) {
            return ResponseEntity.notFound().build();
        }
        merge(campos,  clienteAtual);
        return atualizar(clienteId, clienteAtual);
    }
    private void merge(Map<String, Object> dadosOrigem,
                       Cliente clienteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem =
                objectMapper.convertValue(dadosOrigem,
                        Cliente.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {
            Field field = ReflectionUtils.findField(
                    Cliente.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor =
                    ReflectionUtils.getField(field, clienteOrigem);
            ReflectionUtils.setField(field,
                    clienteDestino, novoValor);
        });
    }
    /*
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
    }*/

}

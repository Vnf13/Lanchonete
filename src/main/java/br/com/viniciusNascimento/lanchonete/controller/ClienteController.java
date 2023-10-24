package br.com.viniciusNascimento.lanchonete.controller;

import br.com.viniciusNascimento.lanchonete.domain.model.Cliente;
import br.com.viniciusNascimento.lanchonete.exception.ClienteNaoEncontradoException;
import br.com.viniciusNascimento.lanchonete.repository.ClienteRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private CadastroClienteService cadastroClienteService;
    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    @GetMapping("/Josimar")
    public List<Cliente> clientePorNome (){
        String nome = "josi";
        return clienteRepository.findByNomeContaining(nome);
    }


    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(()
                        -> new ClienteNaoEncontradoException
                        ("Produto não encontrado"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }
    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long clienteId){
        cadastroClienteService.remover(clienteId);
    }
    @PutMapping("/{clienteId}")
    public ResponseEntity<?>
    atualizar(@PathVariable Long clienteId,
              @RequestBody Cliente cliente){
        Optional<Cliente> clienteAtual
                = clienteRepository.findById(clienteId);
        if(clienteAtual.isPresent()){
            BeanUtils.copyProperties
                    (cliente, clienteAtual.get(), "id");
            Cliente clienteSalvo
                    = cadastroClienteService.salvar(clienteAtual.get());
            return ResponseEntity.ok(clienteSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<?> atualizarParcial
            (@PathVariable Long clienteId,
             @RequestBody Map<String, Object> campos) {
        Optional<Cliente>  clienteAtual
                = clienteRepository.findById(clienteId);
        if (clienteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, clienteAtual.get());
        return atualizar(clienteId, clienteAtual.get());
    }
    private void merge(Map<String, Object> dadosOrigem,
                       Cliente clienteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem =
                objectMapper.convertValue(dadosOrigem,
                        Cliente.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {Field field = ReflectionUtils.findField(
                Cliente.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor =
                    ReflectionUtils.getField(field, clienteOrigem);
            ReflectionUtils.setField(field,
                    clienteDestino, novoValor);
        });
    }
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



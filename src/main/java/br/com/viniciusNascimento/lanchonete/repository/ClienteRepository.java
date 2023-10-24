package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente,Long> {

    List<Cliente> findByNomeContaining(String nome);
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/wp.query
    Optional <Cliente> findFirstByNomeContaining(String nome);
    List<Cliente> findTop2ByNomeContaining(String nome);
    int countByNomeContaining(String nome);
    boolean existsByNomeContaining(String nome);
    Cliente findByCpf(String cpf);
    Cliente findByTelefone(String telefone);
    Optional <Cliente> findByEmail(String email);
    List<Cliente> findByDataNascimento(LocalDate dataNascimento);

  /*  List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);


    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);*/
}

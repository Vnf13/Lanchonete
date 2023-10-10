package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.domain.model.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    @Transactional
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }
    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado",
                Estado.class).getResultList();
    }
    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        Estado estado = findById(id);
        manager.remove(estado);
    }

}


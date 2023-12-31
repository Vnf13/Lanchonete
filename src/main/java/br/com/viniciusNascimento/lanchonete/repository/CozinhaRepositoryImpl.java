package br.com.viniciusNascimento.lanchonete.repository;

import br.com.viniciusNascimento.lanchonete.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
    @Override
    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha",
                Cozinha.class).getResultList();
    }
    @Override
    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }
    /*@Override
    @Transactional
    public void deleteById(Long id) {
        Cozinha cozinha = findById(id);
        manager.remove(cozinha);
    }*/

    @Override
    @Transactional
    public void deleteById(Long id) {
        Cozinha cozinha = findById(id);
        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }

}


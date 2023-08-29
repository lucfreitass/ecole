package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Instrutor;
import br.com.fiap.domain.repository.InstrutorRepository;

import java.util.List;

public class InstrutorService implements Service<Instrutor, Long> {

    InstrutorRepository repository;


    public InstrutorService() {
        repository = InstrutorRepository.of();
    }

    @Override
    public List<Instrutor> findAll() {
        return repository.findAll();
    }

    @Override
    public Instrutor findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public List<Instrutor> findByName(String texto) {
        return repository.findByName( texto );
    }

    @Override
    public Instrutor persist(Instrutor instrutor) {
        return repository.persist( instrutor );
    }
}

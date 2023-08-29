package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Instrutor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class InstrutorRepository implements Repository<Instrutor, Long> {

    private static volatile InstrutorRepository instance;
    private Set<Instrutor> professores;

    private InstrutorRepository() {
        professores = new LinkedHashSet<>();
    }

    /**
     * Padrão Singleton
     *
     * @return
     */
    public static InstrutorRepository of() {
        InstrutorRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized (InstrutorRepository.class) {
            if (instance == null) {
                instance = new InstrutorRepository();
            }
            return instance;
        }
    }


    @Override
    public List<Instrutor> findAll() {
        return professores.stream().toList();
    }

    @Override
    public Instrutor findById(Long id) {
        return professores.stream().filter( p -> p.getId().equals( id ) ).findFirst().orElse( null );
    }

    @Override
    public List<Instrutor> findByName(String texto) {
        return professores.stream().filter( p -> p.getNome().toLowerCase().contains( texto.toLowerCase() ) ).toList();
    }

    @Override
    public Instrutor persist(Instrutor instrutor) {
        if (Objects.isNull( instrutor )) return null;
        if (Objects.isNull( instrutor.getId() )) instrutor.setId( professores.size() + 1L );
        professores.add( instrutor );
        return instrutor;
    }
}

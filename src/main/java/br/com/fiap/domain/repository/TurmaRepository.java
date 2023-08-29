package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Turma;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TurmaRepository implements Repository<Turma, Long> {

    private static volatile TurmaRepository instance;
    private Set<Turma> turmas;

    private TurmaRepository() {
        turmas = new LinkedHashSet<>();
    }

    /**
     * Padrão Singleton
     *
     * @return
     */
    public static TurmaRepository of() {
        TurmaRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized (TurmaRepository.class) {
            if (instance == null) {
                instance = new TurmaRepository();
            }
            return instance;
        }
    }


    @Override
    public List<Turma> findAll() {
        return turmas.stream().toList();
    }

    @Override
    public Turma findById(Long id) {
        return turmas.stream().filter( t -> t.getId().equals( id ) ).findFirst().orElse( null );
    }

    @Override
    public List<Turma> findByName(String texto) {
        return turmas.stream().filter( t -> t.getCurso().getNome().toLowerCase().contains( texto.toLowerCase() ) ).toList();
    }

    @Override
    public Turma persist(Turma turma) {
        if (Objects.isNull( turma )) return null;
        if (Objects.isNull( turma.getId() )) turma.setId( turmas.size() + 1L );
        turmas.add( turma );
        return turma;
    }
}

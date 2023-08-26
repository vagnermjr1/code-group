package repository;

import model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Optional<Pessoa> findById(Long id);

    Optional<Pessoa> findByCpf(Long cpf);
}

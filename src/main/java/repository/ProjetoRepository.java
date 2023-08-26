package repository;


import model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    Optional<Projeto> findById(Long id);



    Optional<List<Projeto>> findAllByPessoas_Id(Long personId);
}

package service;

import dto.MembroDto;
import model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaService {

    Pessoa getPorId(Long personId);
    List<Pessoa> getTodos();
    Pessoa atualizar(Pessoa person);
    void deletar(Long personId);

    Pessoa addPessoaProjeto(MembroDto membroDto);

}

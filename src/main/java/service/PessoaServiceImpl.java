package service;

import dto.MembroDto;
import model.Pessoa;
import model.Projeto;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import repository.PessoaRepository;
import repository.ProjetoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public Pessoa getPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(this.getClass().getName() + " pessoa não encontrada"));
    }

    @Override
    public List<Pessoa> getTodos() {
        return null;
    }

    @Override
    public Pessoa atualizar(Pessoa person) {
       return repository.save(person);
    }

    @Override
    public void deletar(Long personId) {
    repository.deleteById(personId);
    }

    @Override
    public Pessoa addPessoaProjeto(MembroDto membroDto) {
        Pessoa personAdded = new Pessoa();
        Optional<Pessoa> personExistent = null;
        List<Projeto> projectList = new ArrayList<>();

        try {
            Projeto project = projetoRepository.findById(membroDto.getIdProjeto()).get();
            //     Project project = projectService.getProjectById(projectMemberDto.getIdProjeto());
            if (project != null) {
                if (membroDto.getCpf() != null) {
                    // verifica se pessoa já foi cadastrada (Unique: CPF)
                    personExistent = repository.findByCpf(membroDto.getCpf());
                    if (personExistent.isPresent()) {
                        // verifica se pessoa já está em algum projeto
                        List<Projeto> personProjects = projetoRepository.findAllByPessoas_Id(personExistent.get().getId()).get();
                        if (!personProjects.isEmpty()) {
                            return personAdded;
                        }
                        // se pessoa já existe, somente adiciona ao projeto
                        personAdded.getProjetos().add(project);
                    }

                }
                if (membroDto.isFuncionario()) {
                    // mas sendo novo funcionário, cadastra seus dados, e então adiciona ao projeto
                    projectList.add(project);
                    personAdded.setFuncionario(membroDto.isFuncionario());
                    personAdded.setNome(membroDto.getNome());
                    personAdded.setCpf(membroDto.getCpf());
                    personAdded.setCargo(membroDto.getCargo());
                    personAdded.setDataNascimento(membroDto.getDataNascimento());
                    personAdded.setProjetos(projectList);
                    personAdded = repository.save(personAdded);
                }
            }

        } catch (Exception e) {

        }
        return personAdded;
    }


}

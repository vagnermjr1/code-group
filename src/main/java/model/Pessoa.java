package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa extends EntityBase{

    @Column(nullable = false)
    private String nome;

    private Date dataNascimento;

    @Column(length = 14, unique = true)
    private Long cpf;

    private boolean funcionario;

    private String cargo;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Projeto> projetos;
}

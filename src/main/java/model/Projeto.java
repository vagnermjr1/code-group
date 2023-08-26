package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "projeo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto extends EntityBase{

    @Column(length = 200, nullable = false, unique = true)
    private String nome;

    private Date dataInicio;

    private Date dataPrevisaoFim;

    private Date dataFim;

    @Column(length = 5000)
    private String descricao;

    private Long idGerente;

    @Enumerated
    @Column(length = 45)
    private String status;

    @Enumerated
    @Column(length = 45)
    private String risco;

    private Float orcamento;

    @ManyToMany(mappedBy = "projeto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Pessoa> pessoas;
}

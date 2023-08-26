package dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class MembroDto {

    private Long idProjeto;

    @Column(length = 14, unique = true, nullable = false)
    private Long cpf;

    @Column(nullable = false)
    private String nome;

    private String cargo;

    private Date dataNascimento;

    private boolean funcionario;
}

package com.project.softwave.backend_SoftWave.dto;


import com.project.softwave.backend_SoftWave.entity.Tarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;


public class TarefaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private LocalDateTime prazo;


    @Schema(description = "Representa a prioridade da tarefa",
            allowableValues = {
                    "URGENTE", "IMPORTANTE"
            }
    )
    private String prioridade;



    private boolean isFinalizada;

    public TarefaDTO() {

    }


    @Schema(description = "Representa o status da tarefa",
            allowableValues = {
                    "EM DIA", "FINALIZADA","ATRASADA", "SEM PRAZO"
            }
    )
    public String getStatus() {
        if (this.isFinalizada) {
            return "FINALIZADA";
        } else if (this.prazo == null) {
            return "SEM PRAZO";
        } else if (prazo.isBefore(LocalDateTime.now())) {
            return "ATRASADA";
        }
        return "EM DIA";
    }


    public static Tarefa toEntity(TarefaDTO dto) {
        if(dto == null) {
            return null;
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao((dto.getDescricao()));
        tarefa.setPrazo(dto.getPrazo());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setFinalizada(dto.isFinalizada());

        return tarefa;
    }

    public static TarefaDTO toResponseDto(Tarefa tarefa) {
        TarefaDTO responseDto = new TarefaDTO();
        responseDto.setId(tarefa.getId());
        responseDto.setTitulo(tarefa.getTitulo());
        responseDto.setDescricao(tarefa.getDescricao());
        responseDto.setPrazo(tarefa.getPrazo());
        responseDto.setPrioridade(tarefa.getPrioridade());
        responseDto.setFinalizada(tarefa.isFinalizada());
        return responseDto;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }
}

package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnaliseProcessoDTO {

    @NotNull(message = "O ID da análise não pode ser nulo")
    private Integer id;

    @NotNull(message = "O ID da movimentação é obrigatório")
    private Integer movimentacaoId;

    @NotBlank(message = "O resumo da IA não pode estar em branco")
    @Size(max = 10000, message = "O resumo da IA deve ter no máximo 10000 caracteres")
    private String resumoIA;



    public AnaliseProcessoDTO() {
    }

    public AnaliseProcessoDTO(Integer id, Integer movimentacaoId, String resumoIA) {
        this.id = id;
        this.movimentacaoId = movimentacaoId;
        this.resumoIA = resumoIA;
    }


    public static AnaliseProcesso toEntity(AnaliseProcessoDTO dto) {
        if (dto == null) {
            return null;
        }
        AnaliseProcesso analiseProcesso = new AnaliseProcesso();
        analiseProcesso.setId(dto.getId());
        analiseProcesso.setResumoIA(dto.getResumoIA());
        analiseProcesso.setMovimentacaoId(dto.getMovimentacaoId());
        return analiseProcesso;
    }

    public static AnaliseProcessoDTO toDTO(AnaliseProcesso analise) {
        if (analise == null) {
            return null;
        }

        AnaliseProcessoDTO dto = new AnaliseProcessoDTO();
        dto.setId(analise.getId());
        dto.setResumoIA(analise.getResumoIA());
        dto.setMovimentacaoId(analise.getMovimentacaoId());
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(Integer movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public String getResumoIA() {
        return resumoIA;
    }

    public void setResumoIA(String resumoIA) {
        this.resumoIA = resumoIA;
    }


}
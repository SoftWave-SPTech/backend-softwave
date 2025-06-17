package com.project.softwave.backend_SoftWave.dto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para remover um usuário de um processo")
public class RemoverUsuarioProcessoDTO {

    @Schema(description = "ID do processo", example = "1")
    private Integer processoId;

    @Schema(description = "ID do usuário", example = "42")
    private Integer usuarioId;

    public RemoverUsuarioProcessoDTO() {
    }

    public RemoverUsuarioProcessoDTO(Integer processoId, Integer usuarioId) {
        this.processoId = processoId;
        this.usuarioId = usuarioId;
    }

    public Integer getProcessoId() {
        return processoId;
    }

    public void setProcessoId(Integer processoId) {
        this.processoId = processoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}

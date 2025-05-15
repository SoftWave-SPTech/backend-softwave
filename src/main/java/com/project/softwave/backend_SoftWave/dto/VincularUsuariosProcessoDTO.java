package com.project.softwave.backend_SoftWave.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO para vincular usuários a um processo")
public class VincularUsuariosProcessoDTO {

    @Schema(description = "ID do processo", example = "1", required = true)
    private Integer processoId;

    @Schema(description = "Lista de IDs dos usuários a serem vinculados", example = "[42, 43, 44]", required = true)
    private List<Integer> usuariosIds;

    public Integer getProcessoId() {
        return processoId;
    }

    public void setProcessoId(Integer processoId) {
        this.processoId = processoId;
    }

    public List<Integer> getUsuariosIds() {
        return usuariosIds;
    }

    public void setUsuariosIds(List<Integer> usuariosIds) {
        this.usuariosIds = usuariosIds;
    }
}
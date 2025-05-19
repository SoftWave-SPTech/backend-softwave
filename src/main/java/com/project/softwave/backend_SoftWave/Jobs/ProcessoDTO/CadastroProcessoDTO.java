package com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class CadastroProcessoDTO {

    private String numeroProcesso;
    private String descricao;
    private List<Integer> usuarios;



    public CadastroProcessoDTO() {
    }

    public CadastroProcessoDTO(String numeroProcesso, String descricao, List<Integer> usuarios) {
        this.numeroProcesso = numeroProcesso;
        this.descricao = descricao;
        this.usuarios = usuarios;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Integer> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Integer> usuarios) {
        this.usuarios = usuarios;
    }
}

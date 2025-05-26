package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.Usuario;

public class AdvogadosResponseDTO {

    private Integer id;
    private String nomeFantasia;
    private String representante;

    public AdvogadosResponseDTO() {
    }

    public AdvogadosResponseDTO(Integer id, String nomeFantasia) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
    }

    public AdvogadosResponseDTO(Integer id, String nomeFantasia, String representante) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.representante = representante;
    }

    public static AdvogadosResponseDTO toAdvogadosResponseDTO(Usuario usuario) {
        if (usuario instanceof AdvogadoFisico){
            AdvogadoFisico advogado = (AdvogadoFisico) usuario;
            return new AdvogadosResponseDTO(
                advogado.getId(),
                advogado.getNome()
            );
        }else if (usuario instanceof AdvogadoJuridico){
            AdvogadoJuridico advogado = (AdvogadoJuridico) usuario;
            return new AdvogadosResponseDTO(
                advogado.getId(),
                advogado.getNomeFantasia(),
                advogado.getRepresentante()
            );
        }else{
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
}

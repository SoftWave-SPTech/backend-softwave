package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class AdvogadoFisico extends UsuarioFisico {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer oab;

    @ManyToMany
    private List<Processo> processos;

    @OneToMany
    private List<ComentarioProcesso> comentarios;

    @OneToMany
    private List<RegistroFinanceiro> registros;

    @ManyToMany
    private List<Reuniao> reunioes;



    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }
}

package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.Reuniao;
import com.project.softwave.backend_SoftWave.entity.StatusReuniao;
import com.project.softwave.backend_SoftWave.entity.Tarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class ReuniaoDTO {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        private Integer idAdvogado;

        private Integer idCliente;

        @NotBlank
        private LocalDateTime dataHoraInicio;

        @NotBlank
        private LocalDateTime dataHoraFim;

        @NotBlank
        private Double duracao;





        private String plataforma;


        private StatusReuniao statusReuniao;


        @NotNull
        private Boolean confirmacaoCliente;

        @NotNull
        private Boolean confirmacaoAdvogado;


        private String notasAdvogado;

    public ReuniaoDTO() {}
    public ReuniaoDTO(Integer id, Integer idAdvogado, Integer idCliente, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Double duracao, String plataforma, StatusReuniao statusReuniao, Boolean confirmacaoCliente, Boolean confirmacaoAdvogado, String notasAdvogado) {
        this.id = id;
        this.idAdvogado = idAdvogado;
        this.idCliente = idCliente;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.duracao = duracao;
        this.plataforma = plataforma;
        this.statusReuniao = statusReuniao;
        this.confirmacaoCliente = confirmacaoCliente;
        this.confirmacaoAdvogado = confirmacaoAdvogado;
        this.notasAdvogado = notasAdvogado;
    }





    public Integer getId(Integer id) {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public LocalDateTime getDataHoraInicio() {
            return dataHoraInicio;
        }

        public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
            this.dataHoraInicio = dataHoraInicio;
        }

        public LocalDateTime getDataHoraFim() {
            return dataHoraFim;
        }

        public void setDataHoraFim(LocalDateTime dataHoraFim) {
            this.dataHoraFim = dataHoraFim;
        }

        public Double getDuracao() {
            return duracao;
        }

        public void setDuracao(Double duracao) {
            this.duracao = duracao;
        }


        public String getPlataforma() {
            return plataforma;
        }

        public void setPlataforma(String plataforma) {
            this.plataforma = plataforma;
        }

        public StatusReuniao getStatusReuniao() {
            return statusReuniao;
        }

        public void setStatusReuniao(StatusReuniao statusReuniao) {
            this.statusReuniao = statusReuniao;
        }

        public Boolean getConfirmacaoCliente() {
            return confirmacaoCliente;
        }

        public void setConfirmacaoCliente(Boolean confirmacaoCliente) {
            this.confirmacaoCliente = confirmacaoCliente;
        }

        public Boolean getConfirmacaoAdvogado() {
            return confirmacaoAdvogado;
        }

        public void setConfirmacaoAdvogado(Boolean confirmacaoAdvogado) {
            this.confirmacaoAdvogado = confirmacaoAdvogado;
        }

        public String getNotasAdvogado() {
            return notasAdvogado;
        }

        public void setNotasAdvogado(String notasAdvogado) {
            this.notasAdvogado = notasAdvogado;
        }

        public Integer getId() {
            return id;
        }

        public Integer getIdAdvogado() {
            return idAdvogado;
        }

        public void setIdAdvogado(Integer idAdvogado) {
            this.idAdvogado = idAdvogado;
        }

        public Integer getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Integer idCliente) {
            this.idCliente = idCliente;
        }
    }


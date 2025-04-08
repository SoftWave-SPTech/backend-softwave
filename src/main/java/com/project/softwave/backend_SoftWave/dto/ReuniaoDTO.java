package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.Reuniao;
import com.project.softwave.backend_SoftWave.entity.StatusReuniao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReuniaoDTO {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private Integer idAdvogado;

        private Integer idCliente;

        @NotNull
        private LocalDateTime dataHoraInicio;

        @NotNull
        private LocalDateTime dataHoraFim;

        @NotNull
        private Double duracao;


        private String plataforma;


        private StatusReuniao statusReuniao;


        @NotNull
        private Boolean confirmacaoCliente;

        @NotNull
        private Boolean confirmacaoAdvogado;


        private String notasAdvogado;

    public ReuniaoDTO() {}

    public static Reuniao toEntity(ReuniaoDTO dto) {
        if (dto == null) return null;

        Reuniao reuniao = new Reuniao();

        reuniao.setIdAdvogado(dto.getIdAdvogado());
        reuniao.setIdCliente(dto.getIdCliente());
        reuniao.setDataHoraInicio(dto.getDataHoraInicio());
        reuniao.setDataHoraFim(dto.getDataHoraFim());
        reuniao.setDuracao(dto.getDuracao());
        reuniao.setPlataforma(dto.getPlataforma());
        reuniao.setStatusReuniao(dto.getStatusReuniao());
        reuniao.setConfirmacaoCliente(dto.getConfirmacaoCliente());
        reuniao.setConfirmacaoAdvogado(dto.getConfirmacaoAdvogado());
        reuniao.setNotasAdvogado(dto.getNotasAdvogado());

        return reuniao;
    }

    public static ReuniaoDTO toResponseDto(Reuniao reuniao) {
        if (reuniao == null) return null;

        ReuniaoDTO responseDto = new ReuniaoDTO();
        responseDto.setId(reuniao.getId());
        responseDto.setIdAdvogado(reuniao.getIdAdvogado());
        responseDto.setIdCliente(reuniao.getIdCliente());
        responseDto.setDataHoraInicio(reuniao.getDataHoraInicio());
        responseDto.setDataHoraFim(reuniao.getDataHoraFim());
        responseDto.setDuracao(reuniao.getDuracao());
        responseDto.setPlataforma(reuniao.getPlataforma());
        responseDto.setStatusReuniao(reuniao.getStatusReuniao());
        responseDto.setConfirmacaoCliente(reuniao.getConfirmacaoCliente());
        responseDto.setConfirmacaoAdvogado(reuniao.getConfirmacaoAdvogado());
        responseDto.setNotasAdvogado(reuniao.getNotasAdvogado());

        return responseDto;
    }

    
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


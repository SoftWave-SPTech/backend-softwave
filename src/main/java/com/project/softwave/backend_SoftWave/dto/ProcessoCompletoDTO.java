package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.UltimasMovimentacoesDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;

import java.util.List;

public class ProcessoCompletoDTO {

    private Integer id;
    private String numeroProcesso;
    private String assunto;
    private String vara;
    private String valorAcao;
    private String area;
    private String foro;
    private String juiz;
    private List<UltimasMovimentacoesDTO> movimentacoes;
    private ComentarioProcessoDTO comentario;
    private List<String> advogados;

    public ProcessoCompletoDTO() {
    }

    public ProcessoCompletoDTO(Processo processo) {
        this.id = processo.getId();
        this.numeroProcesso = processo.getNumeroProcesso();
        this.assunto = processo.getAssunto();
        this.vara = processo.getVara();
        this.valorAcao = processo.getValorAcao();
        this.area = processo.getArea();
        this.foro = processo.getForo();
        this.juiz = processo.getJuiz();

        // Mapeia nomes dos advogados (físicos e jurídicos)
        List<String> nomesAdvogados = processo.getUsuarios().stream()
                .filter(user -> user.getTipoUsuario().equalsIgnoreCase("advogado_fisico") ||
                        user.getTipoUsuario().equalsIgnoreCase("advogado_juridico"))
                .map(user -> {
                    if (user instanceof AdvogadoFisico advogadoFisico) {
                        return advogadoFisico.getNome();
                    } else if (user instanceof AdvogadoJuridico advogadoJuridico) {
                        return advogadoJuridico.getNomeFantasia(); // ou advogadoJuridico.getRazaoSocial() ou advogadoJuridico.getRepresentante()
                    } else {
                        return "Desconhecido";
                    }
                })
                .toList();
        this.advogados = nomesAdvogados;
    }


    public ProcessoCompletoDTO(
            Integer id,
            String numeroProcesso,
            String assunto,
            String vara,
            String valorAcao,
            String area,
            String foro,
            String juiz,
            List<UltimasMovimentacoesDTO> movimentacoes,
            ComentarioProcessoDTO comentario,
            List<String> advogados
    ) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
        this.assunto = assunto;
        this.vara = vara;
        this.valorAcao = valorAcao;
        this.area = area;
        this.foro = foro;
        this.juiz = juiz;
        this.movimentacoes = movimentacoes;
        this.comentario = comentario;
        this.advogados = advogados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(String valorAcao) {
        this.valorAcao = valorAcao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public List<UltimasMovimentacoesDTO> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<UltimasMovimentacoesDTO> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public ComentarioProcessoDTO getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioProcessoDTO comentario) {
        this.comentario = comentario;
    }

    public List<String> getAdvogados() {
        return advogados;
    }

    public void setAdvogados(List<String> advogados) {
        this.advogados = advogados;
    }
}

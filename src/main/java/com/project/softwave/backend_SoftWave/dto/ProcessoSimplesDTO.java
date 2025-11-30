package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;

import java.time.LocalDateTime;

public class ProcessoSimplesDTO {

    private Integer id;
    private String numeroProcesso;
    private String area;
    private String assunto;
    private String autor;
    private String classe;
    private String controle;
    private String descricao;
    private String distribuicao;
    private String executado;
    private String foro;
    private String indiciado;
    private String juiz;
    private double normalizado_valor_acao;
    private String requerente;
    private String requerido;
    private String valor_acao;
    private String vara;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // Construtor padrão necessário para deserialização do Jackson/Redis
    public ProcessoSimplesDTO() {
    }

    public ProcessoSimplesDTO(Integer id, String numeroProcesso, String area, String assunto, String autor, String classe, String controle, String descricao, String distribuicao, String executado, String foro, String indiciado, String juiz, double normalizado_valor_acao, String requerente, String requerido, String valor_acao, String vara, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
        this.area = area;
        this.assunto = assunto;
        this.autor = autor;
        this.classe = classe;
        this.controle = controle;
        this.descricao = descricao;
        this.distribuicao = distribuicao;
        this.executado = executado;
        this.foro = foro;
        this.indiciado = indiciado;
        this.juiz = juiz;
        this.normalizado_valor_acao = normalizado_valor_acao;
        this.requerente = requerente;
        this.requerido = requerido;
        this.valor_acao = valor_acao;
        this.vara = vara;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static ProcessoSimplesDTO toProcessoSimplesDTO(Processo processo){
        return new ProcessoSimplesDTO(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getArea(),
                processo.getAssunto(),
                processo.getAutor(),
                processo.getClasse(),
                processo.getControle(),
                processo.getDescricao(),
                processo.getDistribuicao(),
                processo.getExecutado(),
                processo.getForo(),
                processo.getIndiciado(),
                processo.getJuiz(),
                processo.getNormalizadoValorAcao(),
                processo.getRequerente(),
                processo.getRequerido(),
                processo.getValorAcao(),
                processo.getVara(),
                processo.getCreatedAt(),
                processo.getUpdatedAt()
        );
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDistribuicao() {
        return distribuicao;
    }

    public void setDistribuicao(String distribuicao) {
        this.distribuicao = distribuicao;
    }

    public String getExecutado() {
        return executado;
    }

    public void setExecutado(String executado) {
        this.executado = executado;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    public String getIndiciado() {
        return indiciado;
    }

    public void setIndiciado(String indiciado) {
        this.indiciado = indiciado;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public double getNormalizado_valor_acao() {
        return normalizado_valor_acao;
    }

    public void setNormalizado_valor_acao(double normalizado_valor_acao) {
        this.normalizado_valor_acao = normalizado_valor_acao;
    }

    public String getRequerente() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente = requerente;
    }

    public String getRequerido() {
        return requerido;
    }

    public void setRequerido(String requerido) {
        this.requerido = requerido;
    }

    public String getValor_acao() {
        return valor_acao;
    }

    public void setValor_acao(String valor_acao) {
        this.valor_acao = valor_acao;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}

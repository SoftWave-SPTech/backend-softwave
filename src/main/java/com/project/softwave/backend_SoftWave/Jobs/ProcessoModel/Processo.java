package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import com.project.softwave.backend_SoftWave.entity.*;
import jakarta.persistence.*;

import java.util.List;

/*
Classe para obterProcessos
 */
@Entity
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    //            "processo": "1192602-55.2024.8.26.0100",
    private String numeroProcesso;
//            "classe": "Despejo por Falta de Pagamento Cumulado Com Cobrança",
    private String classe;
//            "assunto": "Locação de Imóvel",
    private String assunto;
//            "foro": "Foro Central Cível",
    private String foro;
//            "vara": "28ª Vara Cível",
    private String vara;
//            "juiz": "FLAVIA POYARES MIRANDA",
    private String juiz;
//            "apensado": "",
    private String apensado;
//            "distribuicao": "04/12/2024 às 18:18 - Livre",
    private String distribuicao;
//            "controle": "2024/004133",
    private String controle;
//            "area": "Cível",
    private String area;
//            "valor_acao": "R$ 36.000,00",
    private String valorAcao;
//            "normalizado_valor_acao": 36000.0,
    private Double normalizadoValorAcao;
//            "autor": null,
    private String autor;
//            "advogado": null,
    private String advogado;
//            "exectdo": null,
    private String executado;
//            "reqte": "Jarbas Alessandro Rocha Marqueze Advogado:  Felipe Lauriano Rocha Marqueze",
    private String requerente;
//            "reqdo": "Edgar Gualberto Quispe Flores",
    private String requerido;
//            "indiciado": null,
    private String indiciado;

    @ManyToMany
    @JoinTable(name = "processo_advogado_fisico",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id"))
    private List<AdvogadoFisico> advogadosFisicos;

    @ManyToMany
    @JoinTable(name = "processo_advogado_juridico",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id"))
    private List<AdvogadoJuridico> advogadosJuridicos;

    @ManyToMany
    @JoinTable(name = "processo_cliente_fisico",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private List<UsuarioFisico> clientesFisicos;

    @ManyToMany
    @JoinTable(name = "processo_cliente_juridico",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private List<UsuarioJuridico> clientesJuridicos;

    @OneToMany
    private List<ComentarioProcesso> comentarios;

    @OneToMany
    private List<Tarefa> tarefas;

    @OneToMany
    private List<RegistroFinanceiro> registros;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//            "ultimas_movimentacoes": [],
//    private UltimasMovimentacoes ultimasMovimentacoes;
////            "peticoes_diversas": [],
//    private PeticoesDiversas peticoesDiversas;
////            "apensos": [],
//    private Apensos apensos;
////            "audiencias": [],
//    private Audiencias audiencias;

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public String getApensado() {
        return apensado;
    }

    public void setApensado(String apensado) {
        this.apensado = apensado;
    }

    public String getDistribuicao() {
        return distribuicao;
    }

    public void setDistribuicao(String distribuicao) {
        this.distribuicao = distribuicao;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(String valorAcao) {
        this.valorAcao = valorAcao;
    }

    public Double getNormalizadoValorAcao() {
        return normalizadoValorAcao;
    }

    public void setNormalizadoValorAcao(Double normalizadoValorAcao) {
        this.normalizadoValorAcao = normalizadoValorAcao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAdvogado() {
        return advogado;
    }

    public void setAdvogado(String advogado) {
        this.advogado = advogado;
    }

    public String getExecutado() {
        return executado;
    }

    public void setExecutado(String executado) {
        this.executado = executado;
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

    public String getIndiciado() {
        return indiciado;
    }

    public void setIndiciado(String indiciado) {
        this.indiciado = indiciado;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "id=" + id +
                ", numeroProcesso='" + numeroProcesso + '\'' +
                ", classe='" + classe + '\'' +
                ", assunto='" + assunto + '\'' +
                ", foro='" + foro + '\'' +
                ", vara='" + vara + '\'' +
                ", juiz='" + juiz + '\'' +
                ", apensado='" + apensado + '\'' +
                ", distribuicao='" + distribuicao + '\'' +
                ", controle='" + controle + '\'' +
                ", area='" + area + '\'' +
                ", valorAcao='" + valorAcao + '\'' +
                ", normalizadoValorAcao=" + normalizadoValorAcao +
                ", autor='" + autor + '\'' +
                ", advogado='" + advogado + '\'' +
                ", executado='" + executado + '\'' +
                ", requerente='" + requerente + '\'' +
                ", requerido='" + requerido + '\'' +
                ", indiciado='" + indiciado + '\'' +
                '}';
    }
}

package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="pessoa_fisica")
public class UsuarioFisico extends Usuario{
    private String nome;
    private String cpf;
    private String rg;

    @OneToMany
    private List<DocumentoPessoal> documentos;

    @ManyToMany
    private List<Processo> processosCliente;

    public UsuarioFisico() {
    }

    public UsuarioFisico(String nome, String cpf, String rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    //
//    public UsuarioFisico(UsuarioFisicoDTO  usuarioFisicoDTO) {
//        super(null, usuarioFisicoDTO.getSenha(), usuarioFisicoDTO.getEmail());
//        this.nome = usuarioFisicoDTO.getNome();
//        this.cpf = usuarioFisicoDTO.getCpf();
//        this.rg = usuarioFisicoDTO.getRg();
//    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }




}

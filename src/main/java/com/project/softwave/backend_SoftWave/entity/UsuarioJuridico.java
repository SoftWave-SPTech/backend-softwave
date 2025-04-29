package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "pessoa_juridica")
public class UsuarioJuridico extends Usuario{
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;

    @OneToMany
    private List<DocumentoPessoal> documentos;

    @ManyToMany
    private List<Processo> processosCliente;

    public UsuarioJuridico(String cnpj, String nomeFantasia, String razaoSocial) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String cnpj, String nomeFantasia, String razaoSocial) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String cnpj, String nomeFantasia, String razaoSocial) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    //    public UsuarioJuridico(UsuarioJuridicoDTO usuarioJuridicoDTO) {
//        super(null, usuarioJuridicoDTO.getSenha(), usuarioJuridicoDTO.getEmail());
//        this.cnpj = usuarioJuridicoDTO.getCnpj();
//        this.nomeFantasia = usuarioJuridicoDTO.getNomeFantasia();
//        this.razaoSocial = usuarioJuridicoDTO.getRazaoSocial();
//    }

    public UsuarioJuridico() {
    }


    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }


}

package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;

import java.util.ArrayList;
import java.util.List;

public class ClienteComProcessosResponseDTO {

    private Integer id;
    private String nome;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String tipoUsuario;
    private Boolean ativo;
    private List<ProcessoSimplesDTO> processos;

    public ClienteComProcessosResponseDTO() {
    }
    public ClienteComProcessosResponseDTO(Integer id, String nome, String nomeFantasia, String email, String telefone, String tipoUsuario, List<ProcessoSimplesDTO> list) {
        this.id = id;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.processos = list;
    }

    public static ClienteComProcessosResponseDTO toClienteComProcessosResponseDTO(Usuario usuario) {
        ClienteComProcessosResponseDTO dto = new ClienteComProcessosResponseDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setTipoUsuario(usuario.getClass().getSimpleName());
        dto.setAtivo(usuario.getAtivo());
        List<Processo> processosList = new ArrayList<>();
        processosList.addAll(usuario.getProcessos());
        List<ProcessoSimplesDTO> processosDTO = new ArrayList<>();
        for (Processo processo : processosList) {
            processosDTO.add(ProcessoSimplesDTO.toProcessoSimplesDTO(processo));
        }
        dto.setProcessos(processosDTO);
        // Preenche os campos específicos de cada tipo
        if (usuario instanceof UsuarioFisico) {
            UsuarioFisico fisico = (UsuarioFisico) usuario;
            dto.setNome(fisico.getNome());
            dto.setNomeFantasia(null);
        } else if (usuario instanceof UsuarioJuridico) {
            UsuarioJuridico juridico = (UsuarioJuridico) usuario;
            dto.setNome(null);
            dto.setNomeFantasia(juridico.getNomeFantasia());
        }
        return dto;
    }

    public static ClienteComProcessosResponseDTO toClienteComProcessosVinculadosAdvogadoResponseDTO(Usuario usuario, Integer advogadoId) {
        ClienteComProcessosResponseDTO dto = new ClienteComProcessosResponseDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setTipoUsuario(usuario.getClass().getSimpleName());
        dto.setAtivo(usuario.getAtivo());

        List<ProcessoSimplesDTO> processosDTO = usuario.getProcessos().stream()
                .filter(p -> p.getUsuarios().stream().anyMatch(u -> u.getId().equals(advogadoId))) // só processos com o advogado
                .map(ProcessoSimplesDTO::toProcessoSimplesDTO)
                .toList();

        dto.setProcessos(processosDTO);

        if (usuario instanceof UsuarioFisico fisico) {
            dto.setNome(fisico.getNome());
            dto.setNomeFantasia(null);
        } else if (usuario instanceof UsuarioJuridico juridico) {
            dto.setNome(null);
            dto.setNomeFantasia(juridico.getNomeFantasia());
        }

        return dto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<ProcessoSimplesDTO> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoSimplesDTO> processos) {
        this.processos = processos;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
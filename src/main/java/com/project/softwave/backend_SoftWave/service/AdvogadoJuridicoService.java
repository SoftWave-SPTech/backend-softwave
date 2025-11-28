package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.exception.*;
import com.project.softwave.backend_SoftWave.repository.AdvogadoJuridicoRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AdvogadoJuridicoService {

    @Autowired
    private AdvogadoJuridicoRepository advogadoJuridicoRepository;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public AdvogadoJuridico cadastrar(AdvogadoJuridico advogadoJuridico) {
        if (advogadoJuridicoRepository.findByEmailEqualsOrCnpjEquals(
                advogadoJuridico.getEmail(), advogadoJuridico.getCnpj()).isPresent()) {
            throw new EntidadeConflitoException("Email ou CNPJ já existe!");
        }
        if (usuarioRepository.oabExistente(advogadoJuridico.getOab()).isPresent()) {
            throw new EntidadeConflitoException("OAB já cadastrada!");
        }
        advogadoJuridico.setRole(Role.ROLE_ADVOGADO);
        advogadoJuridico.setTentativasFalhasLogin(0);
        advogadoJuridico.setAtivo(false);
        return advogadoJuridicoRepository.save(advogadoJuridico);
    }

    public List<AdvogadoJuridico> listar() {
        List<AdvogadoJuridico> advogadosJuridicos = advogadoJuridicoRepository.findAll();

        if (advogadosJuridicos.isEmpty()) {
            throw new NoContentException("Nenhum advogado jurídico encontrado!");
        }

        return advogadosJuridicos;
    }

    @Transactional
    public AdvogadoJuridico atualizar(Integer id, UsuarioJuridicoAtualizacaoDTO dto) {

        AdvogadoJuridico advogado = advogadoJuridicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado não encontrado!"));

        advogado.setNomeFantasia(dto.getNomeFantasia());
        advogado.setEmail(dto.getEmail());
        advogado.setRazaoSocial(dto.getRazaoSocial());
        advogado.setTelefone(dto.getTelefone());
        advogado.setLogradouro(dto.getLogradouro());
        advogado.setCep(dto.getCep());
        advogado.setBairro(dto.getBairro());
        advogado.setCidade(dto.getCidade());
        advogado.setNumero(dto.getNumero());
        advogado.setRepresentante(dto.getRepresentante());
        advogado.setComplemento(dto.getComplemento());

        return advogadoJuridicoRepository.save(advogado);
    }



    public AdvogadoJuridico buscarPorId(Integer id) {
        return advogadoJuridicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado jurídico não encontrado!"));
    }

    public AdvogadoJuridico buscarPorOab(Integer oab) {
        return advogadoJuridicoRepository.findByOab(oab)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado jurídico não encontrado!"));
    }


    public Boolean deletar(Integer id) {
        if (advogadoJuridicoRepository.findById(id).isPresent()) {
            advogadoJuridicoRepository.deleteById(id);
            return true;
        } else {
            throw new EntidadeNaoEncontradaException("Advogado jurídico não encontrado!");
        }
    }

}

package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.repository.AdvogadoJuridicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AdvogadoJuridicoService {

    @Autowired
    private AdvogadoJuridicoRepository advogadoJuridicoRepository;

    @Autowired
    private UserValidator userValidator;

    public AdvogadoJuridico cadastrar(AdvogadoJuridico advogadoJuridico) {
        if (advogadoJuridicoRepository.findByEmailEqualsOrCnpjEquals(
                advogadoJuridico.getEmail(), advogadoJuridico.getCnpj()).isPresent()) {
            throw new EntidadeConflitoException("Email ou CNPJ já existe");
        }

        if (!userValidator.validarSenha(advogadoJuridico.getSenha()) ||
                !userValidator.validarCamposVaziosJuridico(
                        advogadoJuridico.getNomeFantasia(),
                        advogadoJuridico.getRazaoSocial(),
                        advogadoJuridico.getCnpj())) {
            throw new BasicException("Dados inválidos para cadastro.");
        }

        return advogadoJuridicoRepository.save(advogadoJuridico);
    }

    public List<AdvogadoJuridico> listar() {
        List<AdvogadoJuridico> advogadosJuridicos = advogadoJuridicoRepository.findAll();

        if (advogadosJuridicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum advogado jurídico encontrado.");
        }

        return advogadosJuridicos;
    }

    public AdvogadoJuridico atualizar(Integer id, AdvogadoJuridico advogadoJuridico) {
        Optional<AdvogadoJuridico> optional = advogadoJuridicoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Advogado jurídico não encontrado.");
        }

        if (advogadoJuridicoRepository.existsByEmailEqualsOrCnpjEqualsAndIdNot(
                advogadoJuridico.getEmail(), advogadoJuridico.getCnpj(), id)) {
            throw new EntidadeConflitoException("Email ou CNPJ já existe para outro advogado.");
        }

        if (!userValidator.validarSenha(advogadoJuridico.getSenha()) ||
                !userValidator.validarCamposVaziosJuridico(
                        advogadoJuridico.getNomeFantasia(),
                        advogadoJuridico.getRazaoSocial(),
                        advogadoJuridico.getCnpj())) {
            throw new EntidadeConflitoException("Dados do advogado inválidos");
        }

        advogadoJuridico.setId(id);
        return advogadoJuridicoRepository.save(advogadoJuridico);
    }

    public AdvogadoJuridico buscarPorId(Integer id) {
        return advogadoJuridicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado jurídico com ID " + id + " não encontrado."));
    }

    public AdvogadoJuridico buscarPorOab(Integer oab) {
        return advogadoJuridicoRepository.findByOab(oab)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado jurídico com OAB " + oab + " não encontrado."));
    }



    public Boolean deletar(Integer id) {
        if (advogadoJuridicoRepository.findById(id).isPresent()) {
            advogadoJuridicoRepository.deleteById(id);
            return true;
        } else {
            throw new EntidadeNaoEncontradaException("Advogado jurídico não encontrado");
        }
    }

    public AdvogadoJuridico login(AdvogadoJuridico advogadoJuridico) {
        if (advogadoJuridico.getEmail() == null || advogadoJuridico.getSenha() == null) {
            throw new LoginIncorretoException("Email ou senha não podem ser nulos");
        }

        Optional<AdvogadoJuridico> possivelUsuario = advogadoJuridicoRepository
                .findByEmailEqualsAndSenhaEquals(advogadoJuridico.getEmail(), advogadoJuridico.getSenha());

        if (possivelUsuario.isEmpty()) {
            throw new LoginIncorretoException("Email ou senha incorretos");
        }

        return (possivelUsuario.get());
    }
}

package com.project.softwave.backend_SoftWave.service;


import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.exception.DadosInvalidosException;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.repository.AdvogadoFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AdvogadoFisicoService {

    @Autowired
    private AdvogadoFisicoRepository advogadoFisicoRepository;

    @Autowired
    private UserValidator validarUsuarios;

    public AdvogadoFisico cadastrar(AdvogadoFisico advogadoFisico) {
        if (advogadoFisicoRepository.findByEmailEqualsOrCpfEqualsOrRgEquals(
                advogadoFisico.getEmail(), advogadoFisico.getCpf(), advogadoFisico.getRg()).isPresent()){
            throw new EntidadeConflitoException("Email, CPF ou RG já existe");
        }
            advogadoFisico.setRole(Role.ROLE_ADVOGADO);
            advogadoFisico.setAtivo(false);
            advogadoFisico.setStatusUsuario(true);
            return   advogadoFisicoRepository.save(advogadoFisico);
    }

    public List<AdvogadoFisico> listar() {
        List<AdvogadoFisico> advogadosFisicos = advogadoFisicoRepository.findAll();

        if (advogadosFisicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum advogado jurídico encontrado.");
        }

            return advogadosFisicos;
    }

    @Transactional
    public AdvogadoFisico atualizar(Integer id, UsuarioFisicoAtualizacaoDTO dto) {

        AdvogadoFisico advogado = advogadoFisicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado não encontrado com id: " + id));

        advogado.setNome(dto.getNome());
        advogado.setEmail(dto.getEmail());
        advogado.setTelefone(dto.getTelefone());
        advogado.setLogradouro(dto.getLogradouro());
        advogado.setCep(dto.getCep());
        advogado.setBairro(dto.getBairro());
        advogado.setCidade(dto.getCidade());
        advogado.setNumero(dto.getNumero());

        return advogadoFisicoRepository.save(advogado);
    }

    public AdvogadoFisico buscarPorId(Integer id) {
        return advogadoFisicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado físico não encontrado com ID: " + id));
    }

    public AdvogadoFisico buscarPorOab(Integer oab) {
        return advogadoFisicoRepository.findByOab(oab)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado físico não encontrado com OAB: " + oab));
    }

    public Boolean deletar(Integer id) {
        Optional<AdvogadoFisico> advogadoFisicoOptional = advogadoFisicoRepository.findById(id);
        if (advogadoFisicoOptional.isPresent()) {
            advogadoFisicoRepository.deleteById(id);
            return true;
        } else {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
    }

}

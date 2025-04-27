package com.project.softwave.backend_SoftWave.service;


import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.repository.AdvogadoFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdvogadoFisico cadastrar(AdvogadoFisico advogadoFisico) {


        if (
                    advogadoFisicoRepository.findByEmailEqualsOrCpfEquals(
                            advogadoFisico.getEmail(),
                            advogadoFisico.getCpf()
                    ).isPresent()
           ) {
                throw new EntidadeConflitoException("Email ou CPF já existe");
        }
        String senhaCriptografada = passwordEncoder.encode(advogadoFisico.getSenha());
        advogadoFisico.setSenha(senhaCriptografada);
        advogadoFisico.setRole(Role.ROLE_ADVOGADO);

            return   advogadoFisicoRepository.save(advogadoFisico);
    }

    public List<AdvogadoFisico> listar() {
        List<AdvogadoFisico> advogadosFisicos = advogadoFisicoRepository.findAll();

        if (advogadosFisicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum advogado jurídico encontrado.");
        }

            return advogadosFisicos;
    }

    public AdvogadoFisico atualizar(Integer id, AdvogadoFisico advogadoFisico) {

        Optional<AdvogadoFisico> advogadoFisicoOptional = advogadoFisicoRepository.findById(id);
        if (advogadoFisicoOptional.isPresent()) {
            if (advogadoFisicoRepository.existsByEmailEqualsOrCpfEqualsAndIdNot(
                    advogadoFisico.getEmail(),
                    advogadoFisico.getCpf(),
                    id)) {
                throw new EntidadeConflitoException("Email ou CPF já existe para outro usuário");
            } else if (validarUsuarios.validarSenha(advogadoFisico.getSenha()) &&
                    validarUsuarios.validarCamposVaziosFisico(
                            advogadoFisico.getNome(),
                            advogadoFisico.getRg())) {


                advogadoFisico.setId(id);
                return advogadoFisicoRepository.save(advogadoFisico);
            } else {
                throw new EntidadeConflitoException("Dados do usuário inválidos");
            }
        } else {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
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

    public AdvogadoFisico login(AdvogadoFisico advogadoFisico) {
        if (advogadoFisico.getEmail() == null || advogadoFisico.getSenha() == null) {
            throw new LoginIncorretoException("Email ou senha não podem ser nulos");
        }

        Optional<AdvogadoFisico> possivelUsuario =
                advogadoFisicoRepository.findByEmailEqualsAndSenhaEquals(
                        advogadoFisico.getEmail(),
                        advogadoFisico.getSenha()
                );

        if (possivelUsuario.isEmpty()) {
            throw new LoginIncorretoException("Email ou senha inválidos");
        }

       AdvogadoFisico advogadoFisicoAutenticado = possivelUsuario.get();

        return advogadoFisicoAutenticado;
    }
}

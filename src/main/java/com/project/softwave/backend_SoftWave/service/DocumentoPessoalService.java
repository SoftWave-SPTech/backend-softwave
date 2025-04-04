package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.DocumentoPessoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoPessoalService {

    @Autowired
    private DocumentoPessoalRepository repository;

    public List<DocumentoPessoal> listarDocumentos() {
        return repository.findAll();
    }

    public DocumentoPessoal buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Documento com ID %d não encontrado".formatted(id))
        );
    }

    public DocumentoPessoal cadastrarDocumento(DocumentoPessoal documento) {
        List<DocumentoPessoal> documentosExistentes = repository.findByDocumentoContainingIgnoreCase(documento.getDocumento());

        if (!documentosExistentes.isEmpty()) {
            throw new EntidadeConflitoException("Já existe um documento com esse nome.");
        }

        return repository.save(documento);
    }

    public DocumentoPessoal atualizarDocumento(DocumentoPessoal documentoAtualizado, Integer id) {
        boolean existeDocumento = repository.existsById(id);
        if (!existeDocumento) {
            throw new EntidadeNaoEncontradaException("Documento com ID %d não encontrado".formatted(id));
        }

        documentoAtualizado.setId(id);
        return repository.save(documentoAtualizado);
    }

    public void deletarDocumento(Integer id) {
        boolean existeDocumento = repository.existsById(id);
        if (!existeDocumento) {
            throw new EntidadeNaoEncontradaException("Documento com ID %d não encontrado".formatted(id));
        }

        repository.deleteById(id);
    }
}


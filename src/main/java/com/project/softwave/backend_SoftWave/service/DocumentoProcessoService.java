package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.DocumentoProcessoCadastroDto;
import com.project.softwave.backend_SoftWave.entity.DocumentosProcesso;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.DocumentoProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentoProcessoService {

    @Autowired
    private DocumentoProcessoRepository repository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Value("${file.PASTA_DOCUMENTOS_PROCESSOS}")
    private String PASTA_DOCUMENTOS_PROCESSOS;

    public List<DocumentosProcesso> listar(){return repository.findAll();}

    public DocumentosProcesso buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Documento com ID %d não encontrado".formatted(id))
        );
    }

    public String cadastrarDocumento(DocumentoProcessoCadastroDto documento) throws IOException {File diretorio = new File(PASTA_DOCUMENTOS_PROCESSOS);
       if (!diretorio.exists()) {
            diretorio.mkdirs();
       }

        String nomeOriginalArquivo = documento.getDocumentoProcesso().getOriginalFilename();
        Path caminhoCompletoDocumento = Paths.get(diretorio.toString(), nomeOriginalArquivo);
        Files.write(caminhoCompletoDocumento, documento.getDocumentoProcesso().getBytes());

        Processo processo = processoRepository.findById(documento.getIdProcesso())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado"));

        DocumentosProcesso documentoParaSalvar = new DocumentosProcesso(
                documento.getNomeArquivo(),
                caminhoCompletoDocumento.toString(),
                processo
        );
        repository.save(documentoParaSalvar);

        return caminhoCompletoDocumento.toString();
    }

    public void deletarDocumento(Integer id) throws IOException{
        DocumentosProcesso documentoProcesso = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Documento não encontrado"));

        Files.deleteIfExists(Paths.get(documentoProcesso.getUrlArquivo()));
        repository.deleteById(id);
    }

    public List<DocumentosProcesso> buscarDocumentosProcesso(Integer id){
        if(processoRepository.findById(id).isPresent()){
            return repository.findByFkProcessoId(id);
        }else {
            throw new EntidadeNaoEncontradaException("Processo não encontrado");
        }
    }
}

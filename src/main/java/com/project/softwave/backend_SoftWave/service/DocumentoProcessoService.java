package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.DocumentoProcessoCadastroDto;
import com.project.softwave.backend_SoftWave.entity.DocumentosProcesso;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.DocumentoProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentoProcessoService {

    @Autowired
    private DocumentoProcessoRepository repository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private com.project.softwave.backend_SoftWave.integracao.S3MicroserviceClient s3MicroserviceClient;


    @Value("${file.PASTA_DOCUMENTOS_PROCESSOS}")
    private String PASTA_DOCUMENTOS_PROCESSOS;

    public List<DocumentosProcesso> listar(){
        List<DocumentosProcesso> todos = repository.findAll();

        if(todos.isEmpty()){
            throw  new NoContentException("Nenhum documento processo encontrado!");
        }

        return todos;
    }

    public DocumentosProcesso buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Documento não encontrado!")
        );
    }

    public String cadastrarDocumento(DocumentoProcessoCadastroDto documento) throws IOException {
        // Upload via microserviço
        var uploadResponse = s3MicroserviceClient.uploadFile("docs/processos", documento.getDocumentoProcesso());
        String url = uploadResponse.getUrl();
        String key = uploadResponse.getKey();


        Processo processo = processoRepository.findById(documento.getIdProcesso())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado!"));

        DocumentosProcesso documentoParaSalvar = new DocumentosProcesso(
                documento.getNomeArquivo(),
                url,
                key,
                processo
        );
        repository.save(documentoParaSalvar);

        return url;
    }


    public void deletarDocumento(Integer id) throws IOException {
        DocumentosProcesso documentoProcesso = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Documento não encontrado!"));

        // Extrai a key a partir da URL pública
        String key = documentoProcesso.getUrlArquivo()
                .replace("https://softwave-arquivos-prod.s3.amazonaws.com/", "");

        // Solicita exclusão ao microserviço
        s3MicroserviceClient.deleteFile(key);

        // Remove o registro do banco
        repository.deleteById(id);
    }


    public List<DocumentosProcesso> buscarDocumentosProcesso(Integer id){
        if(processoRepository.findById(id).isPresent()){
            return repository.findByFkProcessoId(id);
        }else {
            throw new EntidadeNaoEncontradaException("Processo não encontrado!");
        }
    }

    public String gerarPresignedUrl(String key) {
        return s3MicroserviceClient.generatePresignedUrl(key);
    }

}

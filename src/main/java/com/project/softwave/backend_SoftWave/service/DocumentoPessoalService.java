package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.DocumentoPessoalCadastroDto;
import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.DocumentoPessoalRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentoPessoalService {

    @Autowired
    private DocumentoPessoalRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private com.project.softwave.backend_SoftWave.integracao.S3MicroserviceClient s3MicroserviceClient;


    @Value("${file.PASTA_DOCUMENTOS_PESSOAIS}")
    private  String PASTA_DOCUMENTOS_PESSOAIS;

    public List<DocumentoPessoal> listarDocumentos() {
        List<DocumentoPessoal> todos = repository.findAll();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhum documento pessoal encontrado!");
        }

        return todos;
    }

    public DocumentoPessoal buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Documento não encontrado!")
        );
    }

    public String cadastrarDocumento(DocumentoPessoalCadastroDto documento) throws IOException {
        // Faz o upload via microserviço
        var uploadResponse = s3MicroserviceClient.uploadFile("docs/pessoais", documento.getDocumentoPessoal());
        String url = uploadResponse.getUrl();
        String key = uploadResponse.getKey();

        // Busca o usuário no banco
        Usuario usuario = usuarioRepository.findById(documento.getIdUsuario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        // Cria entidade e salva no banco
        DocumentoPessoal documentoPessoalParaSalvar = new DocumentoPessoal(
                documento.getNomeArquivo(),
                url,
                key, // <-- novo campo que você vai adicionar na entidade
                usuario
        );
        repository.save(documentoPessoalParaSalvar);

        return url;
    }



//    public DocumentoPessoal atualizarDocumento(DocumentoPessoal documentoAtualizado, Integer id) {
//        boolean existeDocumento = repository.existsById(id);
//        if (!existeDocumento) {
//            throw new EntidadeNaoEncontradaException("Documento com ID %d não encontrado".formatted(id));
//        }
//
//        documentoAtualizado.setId(id);
//        return repository.save(documentoAtualizado);
//    }

    public void deletarDocumento(Integer id) throws IOException {
        DocumentoPessoal documentoPessoal = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Documento não encontrado!"));

        // Extrai a key da URL (ex: docs/pessoais/arquivo.png)
        String key = documentoPessoal.getUrlArquivo()
                .replace("https://softwave-arquivos-prod.s3.amazonaws.com/", "");

        // Chama o microserviço para deletar
        s3MicroserviceClient.deleteFile(key);

        // Remove o registro do banco
        repository.deleteById(id);
    }



    public List<DocumentoPessoal> buscarDocumentosUsuario(Integer id){
        if (usuarioRepository.findById(id).isPresent()) {
            return repository.findByFkUsuarioId(id);
        }else{
            throw new EntidadeNaoEncontradaException("Usuário não encontrado!");
        }
    }

    public String gerarPresignedUrl(String key) {
        return s3MicroserviceClient.generatePresignedUrl(key);
    }

}


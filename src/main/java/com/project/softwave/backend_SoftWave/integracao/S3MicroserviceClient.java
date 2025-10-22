package com.project.softwave.backend_SoftWave.integracao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

@Component
public class S3MicroserviceClient {

    @Value("${microservice.s3.url}")
    private String s3ServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 🔹 Faz o upload de um arquivo para o microserviço S3 e retorna a key e a URL pública.
     */
    public UploadResponse uploadFile(String folder, MultipartFile file) throws IOException {
        String url = s3ServiceUrl + "/files/upload?folder=" + folder;

        // Corpo multipart (arquivo)
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        // Cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Cria requisição
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Envia o POST e espera um JSON no formato {"url": "...", "key": "..."}
        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

        String fileUrl = null;
        String fileKey = null;

        if (response.getBody() != null) {
            Object urlValue = response.getBody().get("url");
            Object keyValue = response.getBody().get("key");

            fileUrl = urlValue != null ? urlValue.toString() : null;
            fileKey = keyValue != null ? keyValue.toString() : null;
        }

        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setUrl(fileUrl);
        uploadResponse.setKey(fileKey);

        return uploadResponse;
    }

    /**
     * 🔹 Solicita ao microserviço a exclusão de um arquivo no S3.
     */
    public void deleteFile(String key) {
        String url = s3ServiceUrl + "/files/delete?key=" + key;
        restTemplate.delete(url);
    }

    /**
     * 🔹 Gera uma URL temporária (pré-assinada) para download de um arquivo.
     * O microserviço retorna um JSON no formato: {"url": "https://..."}
     */
    public String generatePresignedUrl(String key) {
        // Caso o campo venha com URL completa, remove o domínio
        if (key.contains("https://")) {
            key = key.replace("https://softwave-arquivos-prod.s3.amazonaws.com/", "");
        }

        // Monta a URL do microserviço
        String url = s3ServiceUrl + "/files/download?key=" + key + "&minutes=5";

        // Faz a requisição e espera um JSON {"url": "..."}
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getBody() != null && response.getBody().get("url") != null) {
            return response.getBody().get("url").toString();
        }

        return null;
    }
}

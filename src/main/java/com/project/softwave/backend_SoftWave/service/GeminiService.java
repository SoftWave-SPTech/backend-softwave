package com.project.softwave.backend_SoftWave.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.entity.Processo;
import com.project.softwave.backend_SoftWave.repository.AnaliseProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.ProcessoRepository;

import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GeminiService {

    private final ProcessoRepository processoRepository;
    private final AnaliseProcessoRepository analiseRepository;
    private final String apiKey = "AIzaSyAtYHo_lun-el1wWyDirq06awLH7G74uMI";

    public GeminiService(ProcessoRepository processoRepository,
                         AnaliseProcessoRepository analiseRepository) {
        this.processoRepository = processoRepository;
        this.analiseRepository = analiseRepository;
    }

    public void gerarAnalises() {
        List<Processo> processos = processoRepository.findAll();

        for (Processo processo : processos) {
            String prompt = "Explique de forma simples a seguinte movimentação processual:\n\n"
                    + processo.getDescricao()
                    + "\n\nUse uma linguagem clara para leigos e destaque os eventos mais importantes.";

            try {
                String resposta = chamarGemini(prompt);
                AnaliseProcesso analise = new AnaliseProcesso();
                analise.setProcesso(processo);
                analise.setResumoIA(resposta);
                analiseRepository.save(analise);

            } catch (Exception e) {
                System.err.println("Erro ao processar processo " + processo.getNumero() + ": " + e.getMessage());
            }
        }
    }

    private String chamarGemini(String prompt) throws Exception {

        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

        String requestBody = String.format("""
        {
          "contents": [{
            "parts": [{
              "text": "%s"
            }]
          }],
          "generationConfig": {
            "temperature": 0.7,
            "maxOutputTokens": 1000
          }
        }
        """, prompt.replace("\"", "\\\""));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        if (root.has("error")) {
            String mensagemErro = root.path("error").path("message").asText();
            throw new RuntimeException("Erro da IA: " + mensagemErro);
        }

        return root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText()
                .trim();
    }


}

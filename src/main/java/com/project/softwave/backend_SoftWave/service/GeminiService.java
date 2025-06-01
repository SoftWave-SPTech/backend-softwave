package com.project.softwave.backend_SoftWave.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.exception.DadosInvalidosException;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.AnaliseProcessoRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GeminiService {

    private final AnaliseProcessoRepository analiseRepository;
    private final UltimasMovimentacoesRepository ultimasMovimentacoesRepository;

    private final String apiKey =  "AIzaSyAtYHo_lun-el1wWyDirq06awLH7G74uMI";

    public GeminiService(
            AnaliseProcessoRepository analiseRepository,
            UltimasMovimentacoesRepository ultimasMovimentacoesRepository) {
        this.analiseRepository = analiseRepository;
        this.ultimasMovimentacoesRepository = ultimasMovimentacoesRepository;
    }

    public void gerarAnalisePorId(Integer movimentacaoId) {
        ultimasMovimentacoesRepository.findById(movimentacaoId).ifPresentOrElse(movimentacao -> {

            boolean jaTemAnalise = analiseRepository.findByMovimentacoesId(movimentacao.getId()).isPresent();

            if (jaTemAnalise) {
                throw new EntidadeConflitoException("Análise já existente para a movimentação: " + movimentacaoId);
            }

            String prompt = "Explique de forma simples a seguinte movimentação processual:\n\n"
                    + movimentacao.getMovimento() + "\n\n"
                    + "Use uma linguagem clara para leigos em juridiquês e destaque os eventos mais importantes.";

            try {
                String resposta = chamarGemini(prompt);
                resposta = resposta.replace("*", "").trim();

                AnaliseProcesso analise = new AnaliseProcesso();
                analise.setMovimentacoes(movimentacao);
                analise.setResumoIA(resposta);
                analiseRepository.save(analise);

            } catch (Exception e) {
                throw new DadosInvalidosException("Erro ao gerar análise da movimentação " + movimentacaoId);
            }

        }, () -> {
            throw new EntidadeNaoEncontradaException("Movimentação com ID " + movimentacaoId + " não encontrada.");
        });
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
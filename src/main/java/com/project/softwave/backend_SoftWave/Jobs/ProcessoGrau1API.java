package com.project.softwave.backend_SoftWave.Jobs;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.*;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.*;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessoGrau1API {

    private final ApensosRepository apensosRepository;
    private final AudienciasRepository audienciasRepository;
    private final ProcessoRepository processoRepository;
    private final HistoricoClassesRepository historicoClassesRepository;
    private final PeticoesDiversasRepository peticoesDiversasRepository;
    private final DadosDelegaciaRepository dadosDelegaciaRepository;
    private final UltimasMovimentacoesRepository ultimasMovimentacoesRepository;

    public ProcessoGrau1API(ApensosRepository apensosRepository, AudienciasRepository audienciasRepository, ProcessoRepository processoRepository, HistoricoClassesRepository historicoClassesRepository, PeticoesDiversasRepository peticoesDiversasRepository, DadosDelegaciaRepository dadosDelegaciaRepository, UltimasMovimentacoesRepository ultimasMovimentacoesRepository) {
        this.apensosRepository = apensosRepository;
        this.audienciasRepository = audienciasRepository;
        this.processoRepository = processoRepository;
        this.historicoClassesRepository = historicoClassesRepository;
        this.peticoesDiversasRepository = peticoesDiversasRepository;
        this.dadosDelegaciaRepository = dadosDelegaciaRepository;
        this.ultimasMovimentacoesRepository = ultimasMovimentacoesRepository;
    }

    public void getApiParams() throws IOException {
        HttpPost httppost = new HttpPost("https://api.infosimples.com/api/v2/consultas/tribunal/tjsp/primeiro-grau");
//  "0000005-27.2025.8.26.0008";
//  "509556";
//  "hdVPC0gzW8u6f9cb6cvCC75d-G6Q1brCLjy_NWJG";
//  "600";

        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("processo", ParametrosAPI.getParametroProcesso()));
        params.add(new BasicNameValuePair("parte", ParametrosAPI.getParametroParte()));
        params.add(new BasicNameValuePair("cpf", ParametrosAPI.getParametroCpf()));
        params.add(new BasicNameValuePair("cnpj", ParametrosAPI.getParametroCnpj()));
        params.add(new BasicNameValuePair("rg", ParametrosAPI.getParametroRg()));
        params.add(new BasicNameValuePair("advogado", ParametrosAPI.getParametroAdvogado()));
        params.add(new BasicNameValuePair("oab", ParametrosAPI.getParametroOab()));
        params.add(new BasicNameValuePair("carta_precatoria", ParametrosAPI.getParametroCartaPrecatoria()));
        params.add(new BasicNameValuePair("documento_delegacia", ParametrosAPI.getParametroDocumentoDelegacia()));
        params.add(new BasicNameValuePair("cda", ParametrosAPI.getParametroCda()));
        params.add(new BasicNameValuePair("pagina", ParametrosAPI.getParametroPagina()));
        params.add(new BasicNameValuePair("token", ParametrosAPI.getTOKEN()));
        params.add(new BasicNameValuePair("timeout", ParametrosAPI.getTIMEOUT()));
        httppost.setEntity(new UrlEncodedFormEntity(params));
        getDadosAPi(httppost);

    }

    public void getDadosAPi(HttpPost httppost) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();

        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpclient.execute(httppost)) {
            String body = EntityUtils.toString(httpResponse.getEntity());
            JSONObject response = new JSONObject(body);

            if (response.getInt("code") == 200) {
                System.out.println("Retorno com sucesso: \n" + response.get("data"));
                // Pegando o primeiro objeto do array

                JSONArray dataArray = response.getJSONArray("data");
                JSONObject firstDataObject = dataArray.getJSONObject(0); // Pega o primeiro objeto dentro de "data"
                JSONArray processosObject = firstDataObject.getJSONArray("processos"); // Obtém o valor de "processos"
                System.out.println("Quantidade de Processos encontrados: " + processosObject.length());
//                System.out.println(processosObject);


                for (int i = 0; i < processosObject.length(); i++) {
                    JSONObject processo = processosObject.getJSONObject(i);
                    Processo novoProcesso = getProcesso(processo);
//                    System.out.println(novoProcesso);
                    processoRepository.save(novoProcesso);

                    JSONArray ultimasMovimentacoes = processo.getJSONArray("ultimas_movimentacoes");
                    if (ultimasMovimentacoes.isEmpty()){
                        System.out.println("Ultimas movimentações não informadas");
                    } else {
                        for (Object obj : ultimasMovimentacoes) {
                            UltimasMovimentacoes novaMovimentacao = getUltimasMovimentacoes((JSONObject) obj, novoProcesso);
                            ultimasMovimentacoesRepository.save(novaMovimentacao);
//                            System.out.println(novaMovimentacao);
                        }
                    }
                   JSONArray peticoesDiversas = processo.getJSONArray("peticoes_diversas");
                   if (peticoesDiversas.isEmpty()) {
                       System.out.println("Peticoes diversas não informadas");
                   }else{
                       for (Object obj : peticoesDiversas) {
                           PeticoesDiversas novaPeticao = getPeticoesDiversas((JSONObject) obj, novoProcesso);
                           peticoesDiversasRepository.save(novaPeticao);
//                           System.out.println(novaPeticao);
                       }
                   }

                   JSONArray apensos = processo.getJSONArray("apensos");
                   if (apensos.isEmpty()) {
                       System.out.println("Apensos não informados");
                   }else {
                       for (Object object : apensos) {
                           Apensos novoApenso = getApensos((JSONObject) object, novoProcesso);
                           apensosRepository.save(novoApenso);
//                           System.out.println(novoApenso);
                       }

                   }

                   JSONArray audiencias = processo.getJSONArray("audiencias");
                   if (audiencias.isEmpty()) {
                       System.out.println("Audiências não informadas");
                   }else {
                       for (Object obj : audiencias) {
                           Audiencias audiencia = getAudiencias((JSONObject) obj, novoProcesso);
                           audienciasRepository.save(audiencia);
//                           System.out.println(audiencia);
                       }
                   }

                   JSONArray dadosDelegacia = processo.getJSONArray("dados_da_delegacia");
                   if (dadosDelegacia.isEmpty()){
                          System.out.println("Dados de delegacia não informados");
                   }else {
                       for(Object obj : dadosDelegacia){
                           DadosDelegacia dadosDelegacao = getDadosDelegacia((JSONObject) obj, novoProcesso);
                           dadosDelegaciaRepository.save(dadosDelegacao);
//                           System.out.println(dadosDelegacao);
                       }
                   }

                   JSONArray historicoClasses = processo.getJSONArray("historico_classes");
                   if (historicoClasses.isEmpty()){
                       System.out.println("Dados do historico não informados");
                   }else {
                       for (Object object : historicoClasses) {
                           HistoricoClasses historico = getHistoricoClasses((JSONObject) object, novoProcesso);
                           historicoClassesRepository.save(historico);
//                           System.out.println(historico);
                       }
                   }

                }

            } else if (response.getInt("code") >= 600 && response.getInt("code") <= 799) {
                String mensagem = "Um erro aconteceu. Leia para saber mais:\n";
                mensagem += "Código: " + response.get("code") + " (" + response.get("code_message") + ")\n";
                if (!response.isNull("errors")) {
                    mensagem += response.getJSONArray("errors").join("; ");
                }
                System.out.println(mensagem);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Audiencias getAudiencias(JSONObject audienciaObject, Processo novoProcesso) {
        Audiencias audiencia = new Audiencias();
        audiencia.setData(audienciaObject.getString("data"));
        audiencia.setAudiencia(audienciaObject.getString("audiencia"));
        audiencia.setSituacao(audienciaObject.getString("situacao"));
        audiencia.setQuantidadePessoas(audienciaObject.getString("quantidade_pessoas"));
        audiencia.setProcesso(novoProcesso);
        audienciasRepository.findByDataAndProcessoId(audiencia.getData(), audiencia.getProcesso().getId()).ifPresent(audiencia::setId);
        return audiencia;
    }

    private Apensos getApensos(JSONObject apenso, Processo novoProcesso) {
        Apensos novoApenso = new Apensos();
        String numeroProcessoApenso = apenso.getString("numero");
        novoApenso.setNumeroProcesso(numeroProcessoApenso);
        String classeApenso = apenso.getString("classe");
        novoApenso.setClasse(classeApenso);
        String apensamento = apenso.getString("apensamento");
        novoApenso.setApensamento(apensamento);
        String motivo = apenso.getString("motivo");
        novoApenso.setMotivo(motivo);
        novoApenso.setProcesso(novoProcesso);
        apensosRepository.findByNumeroProcesso(numeroProcessoApenso).ifPresent(novoApenso::setId);
        return novoApenso;
    }

    private PeticoesDiversas getPeticoesDiversas(JSONObject peticoesDiversasObject, Processo novoProcesso) {
        PeticoesDiversas novaPeticao = new PeticoesDiversas();
        String dataPeticao = peticoesDiversasObject.getString("data");
        novaPeticao.setData(dataPeticao);
        String tipoPeticao = peticoesDiversasObject.optString("tipo", "Não Informado");
        novaPeticao.setTipo(tipoPeticao);
        novaPeticao.setProcesso(novoProcesso);
        peticoesDiversasRepository.findByDataAndTipo(dataPeticao, tipoPeticao).ifPresent(novaPeticao::setId);
        return novaPeticao;
    }

    private UltimasMovimentacoes getUltimasMovimentacoes(JSONObject ultimasMovimentacoesObject, Processo novoProcesso) {
        UltimasMovimentacoes novaMovimentacao = new UltimasMovimentacoes();
        String dataMovimentacao = ultimasMovimentacoesObject.getString("data");
        novaMovimentacao.setData(dataMovimentacao);
        String movimento = ultimasMovimentacoesObject.getString("movimento");
        novaMovimentacao.setMovimento(movimento);
        novaMovimentacao.setProcesso(novoProcesso);
        ultimasMovimentacoesRepository.findByMovimentoAndData(movimento,dataMovimentacao).ifPresent(novaMovimentacao::setId);
        return novaMovimentacao;
    }

    private DadosDelegacia getDadosDelegacia(JSONObject dadosDelegaciaObject, Processo novoProcesso) {
        DadosDelegacia novosDados = new DadosDelegacia();
        String documento = dadosDelegaciaObject.getString("documento");
        novosDados.setDocumento(documento);
        String numero = dadosDelegaciaObject.optString("numero");
        dadosDelegaciaRepository.findByNumero(numero).ifPresent(novosDados::setId);
        novosDados.setNumero(numero);
        String distrito = dadosDelegaciaObject.getString("distrito_policial");
        novosDados.setDistritoPolicial(distrito);
        String municipio = dadosDelegaciaObject.optString("municipio");
        novosDados.setMunicipio(municipio);
        novosDados.setProcesso(novoProcesso);
        return novosDados;
    }

    private HistoricoClasses getHistoricoClasses(JSONObject historicoClassesObject, Processo novoProcesso) {
        HistoricoClasses novaHistorico = new HistoricoClasses();
        String data = historicoClassesObject.getString("data");
        String classe = historicoClassesObject.getString("classe");
        historicoClassesRepository.findByDataAndClasse(data, classe).ifPresent(novaHistorico::setId);
        novaHistorico.setData(data);
        novaHistorico.setClasse(classe);
        String tipo = historicoClassesObject.getString("tipo");
        novaHistorico.setTipo(tipo);
        String area = historicoClassesObject.getString("area");
        novaHistorico.setArea(area);
        String motivo = historicoClassesObject.getString("motivo");
        novaHistorico.setMotivo(motivo);
        novaHistorico.setProcesso(novoProcesso);
        return novaHistorico;
    }

    private Processo getProcesso(JSONObject processo){
        Processo novoProcesso = new Processo();
        String numeroProcesso = processo.getString("processo");
        processoRepository.findProcessoByNumeroProcesso(numeroProcesso).ifPresent(novoProcesso::setId);
        novoProcesso.setNumeroProcesso(numeroProcesso);
        String classe = processo.optString("classe","Não informado");
        if (classe.isBlank()){
            classe = "Não informado";
        }
        novoProcesso.setClasse(classe);
        String assunto = processo.optString("assunto", "Não informado");
        if (assunto.isBlank()){
            assunto = "Não informado";
        }
        novoProcesso.setAssunto(assunto);
        String foro = processo.getString("foro");
        novoProcesso.setForo(foro);
        String vara = processo.getString("vara");
        novoProcesso.setVara(vara);
        String juiz = processo.optString("juiz", "Não informado");
        if (juiz.isBlank()){
            juiz = "Não informado";
        }
        novoProcesso.setJuiz(juiz);
        String apensado = processo.optString("apensado", "Não informado");
        if(apensado.isBlank()){
            apensado = "Não informado";
        }
        novoProcesso.setApensado(apensado);
        String distribuicao = processo.getString("distribuicao");
        novoProcesso.setDistribuicao(distribuicao);
        String controle = processo.getString("controle");
        novoProcesso.setControle(controle);
        String area = processo.getString("area");
        novoProcesso.setArea(area);
        String valorAcao = processo.optString("valor_acao", "Não informado");
        if (valorAcao.isBlank()){
            valorAcao = "Não informado";
        }
        novoProcesso.setValorAcao(valorAcao);
        Double valorAcaoNormalizado = processo.optDouble("normalizado_valor_acao", 0.0);
        novoProcesso.setNormalizadoValorAcao(valorAcaoNormalizado);
        String autor = processo.optString("autor", "Não informado");
        novoProcesso.setAutor(autor);
        String requerido = processo.optString("reqdo", "Não informado");
        novoProcesso.setRequerido(requerido);
        String requerente = processo.optString("reqte", "Não informado");
        novoProcesso.setRequerente(requerente);
        String advogado = processo.optString("advogado", "Não informado");
        if (advogado.equals("Não informado")){
            if (requerente.contains("Advogado:")) {
                advogado = requerente.replaceAll(".*Advogado:\\s*", "").trim();
            }else if (requerido.contains("Advogado:")) {
                advogado = requerente.replaceAll(".*Advogado:\\s*", "").trim();
            }else {
                advogado = "Não informado";
            }
        }
        novoProcesso.setAdvogado(advogado);
        String executado = processo.optString("executado", "Não informado");
        novoProcesso.setExecutado(executado);
        String indiciado = processo.optString("indiciado", "Não indiciado");
        novoProcesso.setIndiciado(indiciado);
        return novoProcesso;
    }
}
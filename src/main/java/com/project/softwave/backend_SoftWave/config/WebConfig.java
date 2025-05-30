package com.project.softwave.backend_SoftWave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.PASTA_DOCUMENTOS_PROCESSOS}")
    private String pastaDocumentosProcessos;

    @Value("${file.PASTA_DOCUMENTOS_PESSOAIS}")
    private String pastaDocumentosPessoais;

    @Value("${file.PASTA_FOTOS_PERFIS}")
    private String pastaFotosPerfis;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Acesso para Documentos de Processo
        registry.addResourceHandler("/ArquivosSistemaUsuarios/DocumentosProcessos/**")
                .addResourceLocations("file:" + pastaDocumentosProcessos + "/");

        // Acesso para Documentos Pessoais
        registry.addResourceHandler("/ArquivosSistemaUsuarios/DocumentosPessoais/**")
                .addResourceLocations("file:" + pastaDocumentosPessoais + "/");

        // Acesso para Fotos de Perfil
        registry.addResourceHandler("/ArquivosSistemaUsuarios/FotosPerfis/**")
                .addResourceLocations("file:" + pastaFotosPerfis + "/");
    }
}


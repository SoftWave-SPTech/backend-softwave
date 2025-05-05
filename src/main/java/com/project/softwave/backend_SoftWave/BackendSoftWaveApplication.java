package com.project.softwave.backend_SoftWave;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(
//		info = @Info(title = "Documentação Swagger da API SoftWave",
//		description = "API REST desenvolvida para um sistema de gestão de clientes e processos jurídicos")
//)

public class BackendSoftWaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSoftWaveApplication.class, args);
	}

}

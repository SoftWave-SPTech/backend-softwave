package com.project.softwave.backend_SoftWave.service;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;
    private Environment env;

    public EmailService(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    public void enviarEmailResetSenha(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(email);
        message.setSubject("Redefinição de Senha");

        String resetToken = "Aqui está seu token: " + token;
        String texto = "Para redefinir sua senha, Utilize o codigo::\n\n" + resetToken +
                "\n\nO Token expira em 2 horas.";

        message.setText(texto);
        mailSender.send(message);
    }
}

package com.project.softwave.backend_SoftWave.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(env.getProperty("spring.mail.username"));
            helper.setTo(email);
            helper.setSubject("Redefinição de Senha");

            String htmlContent = """
                        <html>
                        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                            <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
                                <h2 style="color: #333;">Redefinição de Senha</h2>
                                <p style="font-size: 16px; color: #555;">
                                    Para redefinir sua senha, utilize o código abaixo:
                                </p>
                                <div style="margin: 20px 0; padding: 15px; background-color: #f0f0f0; border-left: 5px solid #007bff; font-size: 18px; font-weight: bold; color: #333;">
                                    %s
                                </div>
                                <p style="font-size: 14px; color: #999;">
                                    O token expira em 2 horas.
                                </p>
                            </div>
                        </body>
                        </html>
                    """.formatted(token);

            helper.setText(htmlContent, true); // true para HTML
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Você pode lançar uma exceção personalizada aqui
        }
    }

    // faça uma função para enviar email de token de primeiro acesso com corpo do email igual ao de redefinição de senha
    public void enviarEmailPrimeiroAcesso(@Email String email, String token) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(env.getProperty("spring.mail.username"));
            helper.setTo(email);
            helper.setSubject("Primeiro Acesso");

            String htmlContent = """
                        <html>
                        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                            <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
                                <h2 style="color: #333;">Primeiro Acesso</h2>
                                <p style="font-size: 16px; color: #555;">
                                    Para acessar sua conta pela primeira vez, utilize o código abaixo:
                                </p>
                                <div style="margin: 20px 0; padding: 15px; background-color: #f0f0f0; border-left: 5px solid #007bff; font-size: 18px; font-weight: bold; color: #333;">
                                    %s
                                </div>
                                <p style="font-size: 14px; color: #999;">
                                    O token expira em 2 horas.
                                </p>
                            </div>
                        </body>
                        </html>
                    """.formatted(token);

            helper.setText(htmlContent, true); // true para HTML
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Você pode lançar uma exceção personalizada aqui
        }
    }

}

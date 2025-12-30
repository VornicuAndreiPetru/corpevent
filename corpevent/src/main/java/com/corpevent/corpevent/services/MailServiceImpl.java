package com.corpevent.corpevent.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;

    @Override
    public void sendAccessCode(String to, String code) {
        try{
            String html = loadHtmlTemplate(code);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Codul tau de acces la eveniment");
            helper.setText(html, true);

            mailSender.send(message);
        }catch(MessagingException e){
            throw new RuntimeException("Eroare la trimiterea emailului", e);
        }
    }

    private String loadHtmlTemplate(String code){
        try{
            ClassPathResource resource = new ClassPathResource("templates/access-code-email.html");
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return content.replace("{{CODE}}", code);
        }catch (IOException e){
            throw new RuntimeException("Nu pot incarca template-ul HTML", e);
        }
    }
}

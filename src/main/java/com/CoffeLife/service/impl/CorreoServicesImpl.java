package com.CoffeLife.service.impl;

import com.CoffeLife.service.CorreoServices;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class CorreoServicesImpl implements CorreoServices {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreoHtml(String para, String asunto, String contenidoHtml) throws MessagingException {
        
        System.out.println("Enviando correo a: " + para);
        
        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        try {
            
            helper.setFrom("fide.progra25@gmail.com");
         
            helper.setTo(para);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true);
            mailSender.send(mensaje);
            System.out.println("Correo enviado a: " + para);
        }catch (MessagingException e) {
            System.out.println("Error enviando correo: " + e.getMessage());
            throw e;
        }
    }
}


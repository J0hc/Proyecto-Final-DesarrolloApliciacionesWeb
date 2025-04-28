package com.CoffeLife.service;

import jakarta.mail.MessagingException;

public interface CorreoServices {
    public void enviarCorreoHtml (
    String para,
    String asunto,
    String contenidoHtml
    ) throws MessagingException;    
}

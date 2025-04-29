package com.CoffeLife;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.userdetails.User;


/*  Esta clase es para redirigir según el rol del usuario.
    Se hace de esta manera al tener múltiples paneles*/

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean isVendedor = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_VENDEDOR"));
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

        if (isAdmin) {
            response.sendRedirect("/dashboard/dashboardAdmin");
        } else if (isVendedor) {
            response.sendRedirect("/dashboard/dashboardVendedor");
        } else if (isUser) {
            response.sendRedirect("/dashboard/dashboardUser");
        } 
    }
}

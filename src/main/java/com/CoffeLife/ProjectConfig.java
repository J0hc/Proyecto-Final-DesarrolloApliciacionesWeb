package com.CoffeLife;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers(
                        "/", "/index", "/errores/**",
                        "/pruebas/**", "/registro/**", "/js/**", "/webjars/**", "/img/**", "/css/**",
                        "/carrito/**", "/galeria/mostrar", "/info/**",
                        "/contacto/contacto", "/contacto/gracias", "/promociones/promocionesUser"
                ).permitAll()
                
                        .requestMatchers(
                        "/facturar/carrito",
                        "/dashboard/dashboardUser"
                ).hasRole("USER")
                .requestMatchers(
                        "/dashboard/dashboardVendedor",
                        "/producto/listado", "/categoria/listado", "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                 .requestMatchers(
                        "/producto/**", "/categoria/**", "/usuario/**", "/reportes/**",
                        "/galeria/listado", "/galeria/eliminar/**",
                        "/contacto/eliminar/**", "/dashboard/dashboardAdmin",
                        "/descuentos/**", "/descuentos/eliminar**/",
                        "/promociones/listado", "/promociones/guardar", "/promociones/eliminar/**"
                ).hasRole("ADMIN")
                ).formLogin((form) -> form
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }


    /*@Bean
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**",
                        "/pruebas/**", "/reportes/**",
                        "/registro/**", "/js/**", "/webjars/**", "/img/**", "/css/**",
                        "/carrito/**", "/galeria/mostrar", "/info/**", "/contacto/contacto", "/contacto/gracias").permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/categoria/nuevo", "/categoria/guardar",
                        "/categoria/modificar/**", "/categoria/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar",
                        "/usuario/modificar/**", "/usuario/eliminar/**",
                        "/reportes/**",
                        "/reportes/usuarios",
                        "/galeria/listado",
                        "/galeria/eliminar/**",
                        "/contacto/eliminar/**",
                        "/dashboard/dashboardAdmin"
                ).hasRole("ADMIN")
                .requestMatchers("/dashboard/dashboardAdmin").hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/dashboard/dashboardVendedor").hasRole("VENDEDOR")
                .requestMatchers("/facturar/carrito",
                        "/dashboard/dashboardUser")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
    }*/
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build)
            throws Exception {
        build.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}

package com.trabalho_ia.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * Classe de configuração da aplicação.
 * 
 * Esta classe define beans que são utilizados em diferentes partes do sistema.
 * Neste caso, ela configura o bean {@link RestTemplate}, que é usado para 
 * realizar requisições HTTP a serviços externos.
 * 
 * @author Maria Lorena Muralha Lima
 */
@Configuration
public class AppConfig {
    
    /**
     * Define um bean do tipo {@link RestTemplate}.
     * 
     * O {@code RestTemplate} é um cliente HTTP fornecido pelo Spring Framework,
     * usado para interagir com APIs RESTful e realizar requisições HTTP.
     * 
     * @return Uma instância configurada de {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package net.javaguides.springboot_search_rest_api.config;

import net.javaguides.springboot_search_rest_api.utils.Constantes;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(Util.URL_BASE));
        config.setAllowedMethods(List.of(Constantes.GET, Constantes.POST,
                Constantes.PUT, Constantes.DELETE, Constantes.OPTIONS));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return source;
    }
}
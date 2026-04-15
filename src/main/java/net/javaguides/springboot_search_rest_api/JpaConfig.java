package net.javaguides.springboot_search_rest_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    /*Nom du fichier / classe : n’importe quoi
    Doit être annoté : @Configuration + @EnableJpaAuditing
    Doit être dans le package scanné (même package ou sous-package de @SpringBootApplication)
    */

}

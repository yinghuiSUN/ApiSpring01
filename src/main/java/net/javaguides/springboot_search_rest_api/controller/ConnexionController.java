package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.repository.UtilisateurRepository;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/connexion")
public class ConnexionController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @PostMapping
    ResponseEntity<String> checkConnexion(@RequestBody Utilisateur user) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        String message = utilisateurService.checkConnexion(dto);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}

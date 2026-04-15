package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/connexion")
public class ConnexionController {

    private final UtilisateurService utilisateurService;

    public ConnexionController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("checkConnexion")
    ResponseEntity<String> checkConnexion(@RequestParam(name ="name") String name, @RequestParam (name ="password") String password) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setName(name);
        dto.setPassword(password);
        String message = utilisateurService.checkConnexion(dto);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}

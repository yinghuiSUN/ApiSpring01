package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
import net.javaguides.springboot_search_rest_api.dto.ConnexionDto;
import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ConnexionController {

    private final UtilisateurService utilisateurService;

    public ConnexionController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/checkConnexion")
    ResponseEntity<ApiResponse<String>> checkConnexion(@RequestBody ConnexionDto connexionDto) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setName(connexionDto.getName());
        dto.setPassword(connexionDto.getPassword());
        String message = utilisateurService.checkConnexion(dto);

        return ResponseEntity.ok(new ApiResponse<>(message, message, HttpStatus.OK.value()));

    }
}

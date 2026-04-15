package net.javaguides.springboot_search_rest_api.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = Util.URL_BASE)
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("")
    ResponseEntity<List<String>> findAllUsername() {
        List<UtilisateurDto> retour = utilisateurService.getAllUtilisateur();
        List<String> result = retour.stream().map(UtilisateurDto::getName).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("createUtilisateur")
    ResponseEntity<UtilisateurDto> createCompte(@Valid @RequestBody UtilisateurDto dto) {
        UtilisateurDto result  = utilisateurService.createCompte(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{idUser}")
    ResponseEntity<UtilisateurDto> modifyCompte( @Valid @RequestBody UtilisateurDto dto,
                                                 @PathVariable(name = "idUser") Long idUser) {
        UtilisateurDto result  = utilisateurService.modifyCompte(dto, idUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/checkName")
    ResponseEntity<Boolean> isNameExiste(@RequestParam(name ="name") String name) {
        UtilisateurDto userFind = utilisateurService.getUtilisateurByName(name);
        Boolean result = userFind == null? Boolean.FALSE: Boolean.TRUE;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

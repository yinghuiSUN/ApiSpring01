package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("")
    ResponseEntity<List<String>> findAllUsername() {
        List<UtilisateurDto> retour = utilisateurService.getAllUtilisateur();
        List<String> result = retour.stream().map(UtilisateurDto::getName).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("createUtilisateur")
    ResponseEntity<UtilisateurDto> createCompte( @RequestBody UtilisateurDto dto) {
        UtilisateurDto result  = utilisateurService.createCompte(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{idUser}")
    ResponseEntity<UtilisateurDto> modifyCompte( @RequestBody UtilisateurDto dto,
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

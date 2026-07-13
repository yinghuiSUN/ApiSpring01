package net.javaguides.springboot_search_rest_api.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
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

    @GetMapping("/allUtilisateurName")
    ResponseEntity<ApiResponse<List<String>>> findAllUsername() {
        List<UtilisateurDto> retour = utilisateurService.getAllUtilisateur();
        List<String> result = retour.stream().map(UtilisateurDto::getName).toList();
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_001, result, HttpStatus.OK.value()));
    }
    @GetMapping("/allUtilisateur")
    ResponseEntity<ApiResponse<List<UtilisateurDto>>> findAllUser() {
        List<UtilisateurDto> retour = utilisateurService.getAllUtilisateur();
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_001, retour, HttpStatus.OK.value()));
    }

    @PostMapping("createUtilisateur")
    ResponseEntity<ApiResponse<UtilisateurDto>> createCompte(@Valid @RequestBody UtilisateurDto dto) {
        UtilisateurDto result  = utilisateurService.createCompte(dto);
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_POST_MSG , result, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{idUser}")
    ResponseEntity<ApiResponse<UtilisateurDto>> modifyCompte( @Valid @RequestBody UtilisateurDto dto,
                                                 @PathVariable(name = "idUser") Long idUser) {
        UtilisateurDto result  = utilisateurService.modifyCompte(dto, idUser);
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_UPDATE_MSG , result, HttpStatus.OK.value()));
    }
    @GetMapping("/checkName")
    ResponseEntity<ApiResponse<Boolean>> isNameExiste(@RequestParam(name ="name") String name) {
        UtilisateurDto userFound = utilisateurService.findUserByName(name);
        Boolean result = userFound == null? Boolean.FALSE: Boolean.TRUE;
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_002, result, HttpStatus.OK.value()));
    }
    @GetMapping("/findUserById/{idUser}")
    ResponseEntity<ApiResponse<UtilisateurDto>>findUserById(@PathVariable (name = "idUser") Long idUser) {
        UtilisateurDto retour = utilisateurService.findUserById(idUser);
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_003, retour, HttpStatus.OK.value()));
    }
    @GetMapping("/findUserByName/{name}")
    ResponseEntity<ApiResponse<UtilisateurDto>>findUserByName(@PathVariable (name = "name") String name) {
        UtilisateurDto retour = utilisateurService.findUserByName(name);
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_003, retour, HttpStatus.OK.value()));
    }

    @GetMapping("/findUsers/{idUser}")
    ResponseEntity<ApiResponse<List<UtilisateurDto>>>findUsersByDatascope( @PathVariable(name = "idUser") Long idUser) {
        UtilisateurDto currentUser = utilisateurService.findUserById(idUser);
        List<UtilisateurDto> retour = utilisateurService.findUserByDatascope(currentUser);
        return ResponseEntity.ok(new ApiResponse<>(Util.USER_MSG_001, retour, HttpStatus.OK.value()));
    }

}

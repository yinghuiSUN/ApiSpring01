package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<UtilisateurDto> getAllUtilisateur();

    UtilisateurDto getUtilisateurByName(final String name);

    UtilisateurDto createCompte (final UtilisateurDto dto);

    UtilisateurDto modifyCompte(final UtilisateurDto dto, final Long id);

    String checkConnexion(final UtilisateurDto dto);


}

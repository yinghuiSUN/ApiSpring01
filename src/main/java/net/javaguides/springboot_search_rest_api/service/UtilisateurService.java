package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import java.util.List;

public interface UtilisateurService {
    List<UtilisateurDto> getAllUtilisateur();

    UtilisateurDto createCompte (final UtilisateurDto dto);

    UtilisateurDto modifyCompte(final UtilisateurDto dto, final Long id);

    UtilisateurDto findUserById(final Long idUser);

    UtilisateurDto findUserByName(final String name);

    List<UtilisateurDto> findUserByDatascope(UtilisateurDto dto);

    List<UtilisateurDto> findUsersByDeptId(Long deptId);

}

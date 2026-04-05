package net.javaguides.springboot_search_rest_api.mapper;


import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;

public class UtilisateurMapper {

    public static Utilisateur mapToUtilisateur(UtilisateurDto dto) {
        if (dto == null) return null;
        Utilisateur result = new Utilisateur();
        result.setName(dto.getName());
        result.setPassword(dto.getPassword());
        result.setId(dto.getId());
        return result;
    }

    public static UtilisateurDto mapToUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        UtilisateurDto result = new UtilisateurDto();
        result.setId(utilisateur.getId());
        result.setName(utilisateur.getName());
        result.setPassword(utilisateur.getPassword());
        return result;

    }

}

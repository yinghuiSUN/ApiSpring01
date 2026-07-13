package net.javaguides.springboot_search_rest_api.mapper;


import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.enums.DataScope;
import net.javaguides.springboot_search_rest_api.enums.Role;

public class UtilisateurMapper {

    public static Utilisateur mapToUtilisateur(UtilisateurDto dto) {
        if (dto == null) return null;
        Utilisateur result = new Utilisateur();
        result.setName(dto.getName());
        result.setPassword(dto.getPassword());
        result.setId(dto.getId());
        result.setDeptId(dto.getDeptId());

        //DataScope 是 Role 的内部属性
        //DTO 根本不需要传 dataScope
        result.setRole(Role.valueOf(dto.getRole()));
        Utilisateur manager = new Utilisateur();
        manager.setId(Long.parseLong(dto.getManagerId()));
        result.setManager(manager);
        return result;
    }

    public static UtilisateurDto mapToUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        UtilisateurDto result = new UtilisateurDto();
        result.setId(utilisateur.getId());
        result.setName(utilisateur.getName());
        result.setDeptId(utilisateur.getDeptId());
        result.setPassword(utilisateur.getPassword());
        result.setRole(utilisateur.getRole().name());
        result.setDataScope(utilisateur.getRole().getDataScope().name());
        result.setManagerId(utilisateur.getManager().getId().toString());
        return result;

    }

}

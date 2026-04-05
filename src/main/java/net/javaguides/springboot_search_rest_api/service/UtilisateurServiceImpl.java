package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.mapper.UtilisateurMapper;
import net.javaguides.springboot_search_rest_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public List<UtilisateurDto> getAllUtilisateur() {
        return utilisateurRepository.findAll().stream().map(UtilisateurMapper::mapToUtilisateur).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUtilisateurByName(final String name) {
        Utilisateur retour = utilisateurRepository.findByName(name).orElseThrow(
                 ()-> new RuntimeException("Utilisateur does not exist")
        );
        return UtilisateurMapper.mapToUtilisateur(retour);

    }

    @Override
    public UtilisateurDto createCompte(UtilisateurDto dto) {
        Utilisateur user = UtilisateurMapper.mapToUtilisateur(dto);
        Profile profile = new Profile();
        profile.setUtilisateur(user);
        user.setProfile(profile);

        Utilisateur compteCreate = utilisateurRepository.save(user);
        return UtilisateurMapper.mapToUtilisateur(compteCreate);
    }

    @Override
    public UtilisateurDto modifyCompte(UtilisateurDto dto, Long id) {
        utilisateurRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Utilisateur does not exist")
        );
       Utilisateur result = utilisateurRepository.save(UtilisateurMapper.mapToUtilisateur(dto));
       return UtilisateurMapper.mapToUtilisateur(result);
    }

    @Override
    public String checkConnexion(UtilisateurDto dto) {
        String retour = "";
        Optional<Utilisateur > user = utilisateurRepository.findByName(dto.getName());
        if(user.isEmpty()) {
            retour = "Utilisateur does not exist";
        } else if (!user.get().getPassword().equals(dto.getPassword())) {
            retour = "Password is incorrect";
        } else {
            retour = "Connexion is OK";
        }
        return retour;
    }
}

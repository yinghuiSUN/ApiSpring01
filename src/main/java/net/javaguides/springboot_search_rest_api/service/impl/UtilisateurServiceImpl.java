package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.mapper.UtilisateurMapper;
import net.javaguides.springboot_search_rest_api.repository.UtilisateurRepository;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    // 在 Spring Framework 里，如果你用 @Autowired 做字段注入（field injection），你的类就必须依赖 Spring
    // 才能正常工作（自己 new 会得到 null），而且在做单元测试时也很难替换成 mock 对象；但如果用构造函数注入
    // （constructor injection），你可以在创建对象时手动传入依赖，这样代码既更清晰

    //TODO injection difference a voir
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateur() {
        return utilisateurRepository.findAll().stream().map(UtilisateurMapper::mapToUtilisateur).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUtilisateurByName(final String name) {
        Utilisateur retour = utilisateurRepository.findByName(name).orElseThrow(
                 ()-> new RuntimeException(Util.ERROR_001)
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
            retour = Util.ERROR_001;
        } else if (!user.get().getPassword().equals(dto.getPassword())) {
            retour = Util.ERROR_002;
        } else {
            retour = Util.ERROR_003;
        }
        return retour;
    }
}

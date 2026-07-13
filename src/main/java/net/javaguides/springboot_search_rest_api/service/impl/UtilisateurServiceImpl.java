package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.enums.DataScope;
import net.javaguides.springboot_search_rest_api.mapper.UtilisateurMapper;
import net.javaguides.springboot_search_rest_api.repository.UtilisateurRepository;
import net.javaguides.springboot_search_rest_api.service.DepartmentService;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.javaguides.springboot_search_rest_api.utils.Util.ERROR_001;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final DepartmentService departmentService;

    // 在 Spring Framework 里，如果你用 @Autowired 做字段注入（field injection），你的类就必须依赖 Spring
    // 才能正常工作（自己 new 会得到 null），而且在做单元测试时也很难替换成 mock 对象；但如果用构造函数注入
    // （constructor injection），你可以在创建对象时手动传入依赖，这样代码既更清晰

    //TODO injection difference a voir
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, DepartmentService departmentService) {
        this.utilisateurRepository = utilisateurRepository;
        this.departmentService = departmentService;
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateur() {
        return utilisateurRepository.findAll().stream().filter( u-> !u.getName().equalsIgnoreCase("ADMIN"))
                .map(UtilisateurMapper::mapToUtilisateur).collect(Collectors.toList());
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
                ()-> new RuntimeException(ERROR_001)
        );
       Utilisateur result = utilisateurRepository.save(UtilisateurMapper.mapToUtilisateur(dto));
       return UtilisateurMapper.mapToUtilisateur(result);
    }

    @Override
    public UtilisateurDto findUserById(Long idUser) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException(ERROR_001)
        );
        return  UtilisateurMapper.mapToUtilisateur(utilisateur);
    }

    @Override
    public UtilisateurDto findUserByName(String name) {
        Utilisateur result = utilisateurRepository.findByName(name).orElseThrow(
                () -> new RuntimeException(ERROR_001)
        );
        return UtilisateurMapper.mapToUtilisateur(result);
    }



    @Override
    public List<UtilisateurDto> findUserByDatascope(UtilisateurDto dto) {
        DataScope scope = DataScope.valueOf(dto.getDataScope());
        List <Utilisateur> retour = new ArrayList<>();
        switch (scope) {
            case ALL :
                retour = utilisateurRepository.findAll();
                break;
            case DEPT :
                retour = utilisateurRepository.findByDeptId(dto.getDeptId());
                break;
            case DEPT_AND_CHILD :
                List<Long> deptIds = departmentService.getDeptAndChildrenIds(dto.getDeptId());
                retour = utilisateurRepository.findByDeptIdIn(deptIds);
                break;
            case SELF :
                return List.of(dto);

        }
        return retour.stream().map(UtilisateurMapper::mapToUtilisateur).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDto> findUsersByDeptId(Long deptId) {
        List <Utilisateur> retour = utilisateurRepository.findByDeptId(deptId);
        return retour.stream().map(UtilisateurMapper::mapToUtilisateur).collect(Collectors.toList());
    }

}

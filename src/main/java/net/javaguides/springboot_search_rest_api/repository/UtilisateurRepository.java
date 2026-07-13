package net.javaguides.springboot_search_rest_api.repository;

import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Optional<Utilisateur> findByName(String username);
    List<Utilisateur> findByDeptId(Long deptId);
    List<Utilisateur> findByDeptIdIn(List<Long> deptIds);


}

package net.javaguides.springboot_search_rest_api.repository;

import net.javaguides.springboot_search_rest_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByUtilisateur_IdAndId (Long utilisateurId, Long id);
    List<Task> findByUtilisateur_Id (Long utilisateurId);

}

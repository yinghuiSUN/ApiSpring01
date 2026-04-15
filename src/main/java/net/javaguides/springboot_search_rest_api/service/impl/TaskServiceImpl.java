package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.TaskDto;
import net.javaguides.springboot_search_rest_api.entity.Task;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.mapper.TaskMapper;
import net.javaguides.springboot_search_rest_api.repository.TaskRepository;
import net.javaguides.springboot_search_rest_api.repository.UtilisateurRepository;
import net.javaguides.springboot_search_rest_api.service.TaskService;
import net.javaguides.springboot_search_rest_api.specification.TaskSpecification;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final UtilisateurRepository utilisateurRepository;

    private final TaskRepository taskRepository;

    public TaskServiceImpl(UtilisateurRepository utilisateurRepository, TaskRepository taskRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto createTask(TaskDto dto) {
        Task taskNew = TaskMapper.mapToTask(dto);
        utilisateurRepository.findById(dto.getUserId()).orElseThrow(
                () -> new RuntimeException(Util.ERROR_001)
        );
        // reset id =null sinon JPA considère comme un update au lieu de post
        // taskNew.setId(null);

        // add user et des dates
        Utilisateur user = new Utilisateur();
        user.setId(dto.getUserId());
        taskNew.setUtilisateur(user);

        // save et retourner le résultat
        return TaskMapper.mapToTask(taskRepository.save(taskNew));
    }

    @Override
    public TaskDto modifyTask(Long id, TaskDto dto) {
        Task taskModify = TaskMapper.mapToTask(dto);
        // add user et des dates
        Utilisateur user = new Utilisateur();
        user.setId(dto.getUserId());
        taskModify.setUtilisateur(user);
        // modifier
        Task retour = taskRepository.save(taskModify);
        return TaskMapper.mapToTask(retour);
    }

    @Override
    public TaskDto getTaskByIdAndUserId(Long id, Long idUser) {
        List<Task> list = taskRepository.findByUtilisateur_IdAndId(idUser, id);
        if (list.isEmpty()) {
            // Par exemple, lancer une exception ou retourner null
            throw new RuntimeException("Task not found for userId " + idUser + " and id " + id);
        }
        return TaskMapper.mapToTask(list.get(0));
    }

    @Override
    public List<TaskDto> getFiltreTaskByUserId(Long idUser, String status, String priority, LocalDate dueDate) {
        utilisateurRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException(Util.ERROR_001)
        );
        Specification<Task> spec = Specification
                .where(TaskSpecification.hasStatus(status))
                .and(TaskSpecification.hasPriority(priority))
                .and(TaskSpecification.dueDateBefore(dueDate))
                .and(TaskSpecification.hasUserId(idUser));

        List<Task> list = taskRepository.findAll(spec);
        return list.stream().map(TaskMapper::mapToTask).toList();
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


}

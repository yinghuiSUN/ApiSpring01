package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.TaskDto;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskDto createTask (final TaskDto dto);

    TaskDto modifyTask(final Long id, final TaskDto dto);

    TaskDto getTaskByIdAndUserId(final Long id, final Long userId);

    List<TaskDto> getFiltreTaskByUserId(final Long idUser, final String status, final String priority, final LocalDate dueDate);

    void deleteTaskById(final Long id);
}

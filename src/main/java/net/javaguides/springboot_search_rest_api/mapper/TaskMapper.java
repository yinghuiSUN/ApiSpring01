package net.javaguides.springboot_search_rest_api.mapper;

import net.javaguides.springboot_search_rest_api.dto.TaskDto;
import net.javaguides.springboot_search_rest_api.entity.Task;
import net.javaguides.springboot_search_rest_api.enums.Priority;
import net.javaguides.springboot_search_rest_api.enums.TaskStatus;

public class TaskMapper {

    public static Task mapToTask(TaskDto dto) {
        if (dto == null) return null;

        Task result = new Task();
        result.setId(dto.getId());
        result.setTitle(dto.getTitle());
        result.setDescription(dto.getDescription());
        result.setDueDate(dto.getDueDate());
        result.setStatus(TaskStatus.valueOf(dto.getStatus()));
        result.setPriority(Priority.valueOf(dto.getPriority()));

        return result;
    }

    public static TaskDto mapToTask(Task task) {
        if (task == null) return null;

        TaskDto result = new TaskDto();
        result.setId(task.getId());
        result.setTitle(task.getTitle());
        result.setDescription(task.getDescription());
        result.setDueDate(task.getDueDate());
        result.setStatus(task.getStatus().name());
        result.setPriority(task.getPriority().name());
        result.setUserId(task.getUtilisateur().getId());

        return result;
    }
}

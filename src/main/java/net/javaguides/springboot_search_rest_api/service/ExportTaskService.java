package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.TaskDto;

import java.io.IOException;
import java.util.List;

public interface ExportTaskService {
    void exportTaskTOExcelByIdUser(final Long idUser) throws IOException;

    void exportTaskWithFiltre(List<TaskDto> list) throws  IOException;
}

package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
import net.javaguides.springboot_search_rest_api.dto.TaskDto;
import net.javaguides.springboot_search_rest_api.service.ExportTaskService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/export")
public class ExportController {

    private final ExportTaskService excelTaskService;

    public ExportController(ExportTaskService excelTaskService) {
        this.excelTaskService = excelTaskService;
    }

    @GetMapping("/tasks/{idUser}")
    public ApiResponse<String> exportTaskByIdUser(@PathVariable(name = "idUser") final Long idUser) {
        try {
            excelTaskService.exportTaskTOExcelByIdUser(idUser);
            return new ApiResponse<>(Util.EXPORT_OK, null, HttpStatus.OK.value());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/tasks")
    public ApiResponse<String> exportTaskFiltre(@RequestBody List<TaskDto> tasks) {
        try {
            excelTaskService.exportTaskWithFiltre(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ApiResponse<>(Util.EXPORT_OK, null, HttpStatus.OK.value());
    }
}

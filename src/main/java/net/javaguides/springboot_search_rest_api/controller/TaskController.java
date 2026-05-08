package net.javaguides.springboot_search_rest_api.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
import net.javaguides.springboot_search_rest_api.dto.TaskDto;
import net.javaguides.springboot_search_rest_api.repository.TaskRepository;
import net.javaguides.springboot_search_rest_api.service.TaskService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = Util.URL_BASE)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

   private final TaskService taskService;
   private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/createTask")
    // ajouter @Valid pour que Bean Validation marche
    ResponseEntity<ApiResponse<TaskDto>> createTask(@Valid @RequestBody TaskDto dto){
        TaskDto dtoCreate = taskService.createTask(dto);
        return ResponseEntity.ok(new ApiResponse<>(Util.TASK_POST_MSG, dtoCreate, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{idTask}")
    ResponseEntity<ApiResponse<TaskDto>> modifyTask(@PathVariable(name = "idTask") final Long id,
                                             @Valid @RequestBody TaskDto dto) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException(Util.TASK_MSG_001);
        }
        TaskDto result = taskService.modifyTask(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(Util.TASK_UPDATE_MSG, result, HttpStatus.OK.value()));
        //return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{idTask}/utilisateurs/{idUser}")
    ResponseEntity<ApiResponse<TaskDto>> getTaskByIdAndUserId(@PathVariable(name = "idTask") final Long id,
                                                       @PathVariable(name = "idUser") final Long idUser) {
        TaskDto retour = taskService.getTaskByIdAndUserId(id, idUser);
        return ResponseEntity.ok(new ApiResponse<>("", retour, HttpStatus.OK.value()));
    }

    @GetMapping("/{idUser}")
    ResponseEntity<ApiResponse<List<TaskDto>>> getTaskByUserId(@PathVariable(name = "idUser") final Long idUser,
                                                  @RequestParam(required = false) String status,
                                                  @RequestParam(required = false) String priority,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        List<TaskDto> retour = taskService.getFiltreTaskByUserId(idUser, status, priority, dueDate);

        return ResponseEntity.ok(new ApiResponse<>("", retour, HttpStatus.OK.value()));
    }

    @DeleteMapping("/{idTask}")
    ResponseEntity<ApiResponse<Void>> deleteTaskById(@PathVariable(name = "idTask") final Long id) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException(Util.TASK_MSG_002);
        }
        taskService.deleteTaskById(id);
        return ResponseEntity.ok(new ApiResponse<>(Util.TASK_DELETE_MSG, null, HttpStatus.OK.value()));
    }


}

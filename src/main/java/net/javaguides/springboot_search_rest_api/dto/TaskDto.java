package net.javaguides.springboot_search_rest_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot_search_rest_api.utils.Constantes;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private String priority;
    @NotNull
    private String status;
    // Format ISO 8601
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FORMAT_DATE_ISO)
    private LocalDateTime dueDate;
    @NotNull
    private Long userId;
}

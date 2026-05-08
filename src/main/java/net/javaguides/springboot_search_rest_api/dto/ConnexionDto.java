package net.javaguides.springboot_search_rest_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConnexionDto {
    @NotNull
    private String name;
    @NotNull
    private String password;
}

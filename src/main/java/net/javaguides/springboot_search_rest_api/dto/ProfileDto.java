package net.javaguides.springboot_search_rest_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private String poste;
    @NotNull
    private String competences;
    @NotNull
    private String note;
    private String image;
    private Boolean isActif;
    @NotNull
    private Long userId;
}

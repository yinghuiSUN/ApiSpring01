package net.javaguides.springboot_search_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String name;
    private Integer age;
    private String poste;
    private String competences;
    private String note;
    private String image;
    private Boolean isActif;
}

package net.javaguides.springboot_search_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeptTreeDto {
    private Long id;

    private String name;

    private List<DeptTreeDto> children = new ArrayList<>();
}

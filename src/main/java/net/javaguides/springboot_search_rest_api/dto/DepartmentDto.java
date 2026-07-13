package net.javaguides.springboot_search_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private Long id;

    // 部门名称
    private String name;

    // 上级部门
    private Long parentId;

    // 部门负责人
    private Long leaderId;
}

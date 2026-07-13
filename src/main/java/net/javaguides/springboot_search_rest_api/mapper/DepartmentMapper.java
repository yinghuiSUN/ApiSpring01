package net.javaguides.springboot_search_rest_api.mapper;

import net.javaguides.springboot_search_rest_api.dto.DepartmentDto;
import net.javaguides.springboot_search_rest_api.entity.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDept(Department dept) {
        if (dept == null) return  null;
        DepartmentDto dto = new DepartmentDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setLeaderId(dept.getLeaderId());
        dto.setParentId(dept.getParentId());
        return dto;
    }

    public static Department mapToDept(DepartmentDto dto) {
        if (dto == null) return  null;
        Department dept = new Department();
        dept.setId(dto.getId());
        dept.setName(dto.getName());
        dept.setLeaderId(dto.getLeaderId());
        dept.setParentId(dto.getParentId());
        return dept;
    }
}

package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.DepartmentDto;
import net.javaguides.springboot_search_rest_api.dto.DeptTreeDto;

import java.util.List;

public interface DepartmentService {

     List<Long> getDeptAndChildrenIds(Long deptId);

     List<DepartmentDto> getDeptByRole(String role) ;

     List<DeptTreeDto> getDepartmentTree(String role, Long deptId);
}

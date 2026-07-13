package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.DepartmentDto;
import net.javaguides.springboot_search_rest_api.dto.DeptTreeDto;
import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.entity.Department;
import net.javaguides.springboot_search_rest_api.enums.DataScope;
import net.javaguides.springboot_search_rest_api.enums.Role;
import net.javaguides.springboot_search_rest_api.mapper.DepartmentMapper;
import net.javaguides.springboot_search_rest_api.repository.DepartmentRepository;
import net.javaguides.springboot_search_rest_api.service.DepartmentService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceimpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceimpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * 获取当前部门以及所有子部门
     */
    @Override
    public List<Long> getDeptAndChildrenIds(Long deptId) {
        List<Long> result = new ArrayList<>();
        collectDeptIds(deptId, result);
        return result;
    }

    private void collectDeptIds(Long deptId, List<Long> result) {
        // 自己先加进去
        result.add(deptId);
        // 查子部门
        List<Department> children = departmentRepository.findByParentId(deptId);
        // 递归
        for (Department child : children) {
            collectDeptIds(child.getId(), result);
        }
    }

    @Override
    public List<DepartmentDto> getDeptByRole(String role) {
        List<Department> retour = new ArrayList<>();
        switch (role) {
            case Util.EMPLOYEE:
            case Util.LEAD:
                retour = findByNameList(Util.EMPLOYEE_LEAD_DEPT_LIST);
                break;
            case Util.MANAGER:
                retour = findByNameList(Util.MANAGER_DEPT_LIST);
                break;
            case Util.DIRECTOR:
                //TODo definir plus tard le role de admin et son fct
            case Util.ADMIN:
                retour = findByNameList(Util.DIRECTOR_ADMIN_DEPT_LIST);
                break;
        }

        return retour.stream().map(DepartmentMapper::mapToDept).toList();
    }


    @Override
    public List<DeptTreeDto> getDepartmentTree(String role, Long deptId) {
        Role r = Role.valueOf(role.toUpperCase());
        DataScope scope = r.getDataScope();
        List<Department> departments;

        switch (scope) {
            case ALL:
                departments = departmentRepository.findAll();
                break;
            case DEPT:
                departments = List.of(departmentRepository.findById(deptId).orElseThrow(
                        () -> new RuntimeException("Department not found"))
                    );
                break;
            case DEPT_AND_CHILD:
                List<Long> deptIds = getDeptAndChildrenIds(deptId);
                departments = departmentRepository.findAllById(deptIds);
                break;
            case SELF:
                return Collections.emptyList();
            default:
                return Collections.emptyList();
        }

        return buildTree(departments);
    }

    private List<DeptTreeDto> buildTree(List<Department> departments) {
        Map<Long, DeptTreeDto> map = new HashMap<>();

        for (Department dept : departments) {
            DeptTreeDto dto = new DeptTreeDto();
            dto.setId(dept.getId());
            dto.setName(dept.getName());
            map.put(dto.getId(), dto);
        }

        List<DeptTreeDto> roots = new ArrayList<>();
        for (Department dept : departments) {
            DeptTreeDto dto = map.get(dept.getId());
            boolean isRoot = dept.getParentId() == null
                            || dept.getParentId() == 0
                            || !map.containsKey(dept.getParentId());

            if (isRoot) {
                roots.add(dto);
            } else {
                DeptTreeDto parent =
                        map.get(dept.getParentId());
                parent.getChildren().add(dto);
            }
        }
        return roots;
    }

    private List<Department> findByNameList(List<String> list) {
            return departmentRepository.findByNameIn(list);
    }

}

package net.javaguides.springboot_search_rest_api.controller;


import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
import net.javaguides.springboot_search_rest_api.dto.DepartmentDto;
import net.javaguides.springboot_search_rest_api.dto.DeptTreeDto;
import net.javaguides.springboot_search_rest_api.dto.UtilisateurDto;
import net.javaguides.springboot_search_rest_api.service.DepartmentService;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/{role}")
    public ApiResponse<List<DepartmentDto>> getDeptByRole(@PathVariable(name = "role") String role) {
        // find le nom de departement avec le role
        // ex  lead -> cse, syndicat ;  directors -> XXX service
        List<DepartmentDto> retour = departmentService.getDeptByRole(role.toUpperCase());
        return new ApiResponse<>(Util.DEPARTEMENT_MSG_001, retour, HttpStatus.OK.value());
    }

    @GetMapping("/deptTree")
    public ApiResponse<List<DeptTreeDto>> getDepartmentTree(@RequestParam String role, @RequestParam Long deptId) {

        List<DeptTreeDto> retour =  departmentService.getDepartmentTree(role, deptId);
        return new ApiResponse<>(Util.DEPARTEMENT_MSG_001, retour, HttpStatus.OK.value());
    }

    @GetMapping("/{deptId}/utilisateurs")
    public ApiResponse<List<UtilisateurDto>> getUtilisateursByDeptId(@PathVariable(name = "deptId") Long deptId) {
        List<UtilisateurDto> retour= utilisateurService.findUsersByDeptId(deptId);
        return new ApiResponse<>(Util.DEPARTEMENT_MSG_002, retour, HttpStatus.OK.value());
    }


}

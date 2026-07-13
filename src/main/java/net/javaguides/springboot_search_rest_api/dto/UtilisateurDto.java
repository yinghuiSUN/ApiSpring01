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
public class UtilisateurDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String password;

    //DTO 用 String（更“API化”）
    //前后端完全解耦
    //任意语言都能用
    //更适合 REST API / 微服务
    @NotNull
    private String role;


    private String dataScope;

    // 所属部门

    private Long deptId;

    @NotNull
    private String managerId;
}

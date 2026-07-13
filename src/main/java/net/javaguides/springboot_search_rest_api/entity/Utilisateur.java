package net.javaguides.springboot_search_rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot_search_rest_api.enums.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Task> listTask;

    // 所属部门
    private Long deptId;

    // 一个用户属于一个角色
    @Enumerated(EnumType.STRING)
    private Role role;

    // 一个用户有一个直属上司
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Utilisateur manager;


}

package net.javaguides.springboot_search_rest_api.repository;

import net.javaguides.springboot_search_rest_api.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DepartmentRepository extends JpaRepository <Department, Long>{
    List<Department> findByParentId(Long parentId);
   // le sql devient select *** from *** where name IN ();
    List<Department> findByNameIn(List<String> names);

    List<Department> findAll();

}

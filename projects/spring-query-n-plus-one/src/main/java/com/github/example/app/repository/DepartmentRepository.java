package com.github.example.app.repository;

import com.github.example.app.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * Using @EntityGraph (Annotation on Query Method)
     * ✅ This ensures employees are fetched without lazy loading.
     *
     * @return collections of department
     */
    @EntityGraph(attributePaths = {"employees"})
    List<Department> findAll();

    /**
     * Using @NamedEntityGraph (Defined in Entity)
     * ✅ This will use NamedEntityGraph defined in the Department entity.
     *
     * @param id specific id
     * @return optional department
     */
    @EntityGraph(value = "Department.employees", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Department> findById(Long id);

    /**
     * Using JOIN FETCH in JPQL
     * ✅ This ensures Department & Employees are loaded in one query.
     *
     * @param id specific id
     * @return department data
     */
    @Query("SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id")
    Department findByIdWithEmployees(@Param("id") Long id);
}

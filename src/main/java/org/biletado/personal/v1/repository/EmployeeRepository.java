package org.biletado.personal.v1.repository;

import org.biletado.personal.v1.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findAllByName(String name);
}
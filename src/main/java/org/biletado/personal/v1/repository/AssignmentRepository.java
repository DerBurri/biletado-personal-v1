package org.biletado.personal.v1.repository;

import org.biletado.personal.v1.model.Assignment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AssignmentRepository extends CrudRepository<Assignment, UUID> {


}
package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.Assignment;
import org.biletado.personal.v1.model.Employee;
import org.biletado.personal.v1.model.PersonalAssignmentsGet200Response;
import org.biletado.personal.v1.model.PersonalEmployeesGet200Response;
import org.biletado.personal.v1.repository.AssignmentRepository;
import org.biletado.personal.v1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
@RestController
@RequestMapping("${openapi.biletadoPersonal.base-path:/}")
public class PersonalApiController implements PersonalApi {

    private final NativeWebRequest request;

    @Autowired
    AssignmentRepository assignments;
    @Autowired
    EmployeeRepository employees;

    @Autowired
    public PersonalApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<PersonalEmployeesGet200Response> personalEmployeesGet() {
        getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, employees.findAll());
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> personalEmployeesIdGet(UUID id) {
        Employee employee = employees.findById(id).orElse(null);
        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, employee);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdDelete(UUID id) {
        Employee employee = employees.findById(id).orElse(null);
        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            employees.deleteById(id);
        } catch (DataAccessException e) {
            // deletion not possible because of existing assignments
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // todo 401 if no (valid) authentication is given
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdPut(UUID id, Employee employee) {
        if (!id.equals(employee.getId())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Employee employeeFromDb = employees.findById(id).orElse(null);
        if (employeeFromDb == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            employees.save(employee);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // todo 401 if no (valid) authentication is given
    }

    @Override
    public ResponseEntity<Employee> personalEmployeesPost(Employee employee) {
        Employee eFromDb = employees.findById(employee.getId()).orElse(null);
        try {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setEntityJsonResponse(request, employees.save(employee));
            });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (eFromDb == null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonalAssignmentsGet200Response> personalAssignmentsGet(UUID employeeId, UUID reservationId) {
        getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, assignments.findAll());
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsIdGet(UUID id) {
        Assignment assignment = assignments.findById(id).orElse(null);
        if (assignment == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, assignment);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> personalAssignmentsIdDelete(UUID id) {
        Assignment assignment = assignments.findById(id).orElse(null);
        if (assignment == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            employees.deleteById(id);
        } catch (DataAccessException e) {
            //Placeholder for DataAccessException
        }
        return new ResponseEntity<>(HttpStatus.OK);
        // todo 401 if no (valid) authentication is given
    }

    @Override
    public ResponseEntity<Void> personalAssignmentsIdPut(UUID id, Assignment assignment) {
        if (id.equals(assignment.getId())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Assignment assignmentFromDb = assignments.findById(id).orElse(null);
        if (assignmentFromDb == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            assignments.save(assignment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // todo 401 if no (valid) authentication is given
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsPost(Assignment assignment) {
        Assignment aFromDb = assignments.findById(assignment.getId()).orElse(null);

        // 422 if the reservation already has an assignment with the given role
        Iterable<Assignment> allAssignments = assignments.findAll();
        for (Assignment ass : allAssignments) {
            if (ass.getReservationId() == assignment.getReservationId() && ass.getRole() == assignment.getRole()) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        // 422 if employee does not exist or the reservation does not exist
        if (employees.findById(assignment.getId()).orElse(null) == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        // todo api call reservations


        try {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setEntityJsonResponse(request, assignments.save(assignment));
            });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (aFromDb == null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

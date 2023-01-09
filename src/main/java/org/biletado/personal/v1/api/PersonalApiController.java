package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.*;
import org.biletado.personal.v1.repository.AssignmentRepository;
import org.biletado.personal.v1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
@RestController
@RequestMapping("${openapi.biletadoPersonal.base-path:/}")
public class PersonalApiController implements PersonalApi {

    private final NativeWebRequest request;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid input")
    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public void handleException() {

    }

    @Autowired
    ReservationsCaller reservationsCaller;
    @Autowired
    AssignmentRepository assignments;
    @Autowired
    EmployeeRepository employees;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;


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
        /*getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, employees.findAll());
        });*/
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        PersonalEmployeesGet200Response response = new PersonalEmployeesGet200Response();
        response.employees(employees);
        return ResponseEntity.ok(response);
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
            getRequest().ifPresent(request ->
            {
                ApiUtil.setMessageResponse(request, "deletion not possible because of existing assignments");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdPut(UUID id, Employee employee) {
        if (!id.equals(employee.getId())) {
            if (!employee.isIdSet()) {
                employee.setId(id);
            } else {
                getRequest().ifPresent(request ->
                {
                    ApiUtil.setMessageResponse(request, "mismatching id in url and object");
                });
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        try {
            employees.save(employee);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
//        getRequest().ifPresent(request ->
//        {
//            ApiUtil.setEntityJsonResponse(request, assignments.findAll());
//        });
        List<Assignment> assignments = new ArrayList<>();
        assignmentRepository.findAll().forEach(assignments::add);
        PersonalAssignmentsGet200Response response = new PersonalAssignmentsGet200Response();
        response.assignments(assignments);
        return ResponseEntity.ok(response);
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
            assignments.deleteById(id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Optional<ResponseEntity> newAssignment(Assignment assignment) {
        // 422 if employee does not exist or the reservation does not exist
        if (employees.findById(assignment.getEmployeeId()).orElse(null) == null) {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setMessageResponse(request, "employee does not exist");
            });
            return Optional.of(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        }
        // check reservations api if reservation exists
        try {
            Reservation reservation = reservationsCaller.getReservationsFromId(assignment.getReservationId());
        } catch (Exception e) { // 404 or api down
            getRequest().ifPresent(request ->
            {
                ApiUtil.setMessageResponse(request, "reservation does not exist / not found or veryfied");
            });
            return Optional.of(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        }


        // 422 if the reservation already has an assignment with the given role
        Iterable<Assignment> allAssignments = assignments.findAllByReservationId(assignment.getReservationId());
        for (Assignment ass : allAssignments) {
            if (!(ass.getId().equals(assignment.getId())) && // check if the object is not just getting updated
                    ass.getReservationId().equals(assignment.getReservationId()) && ass.getRole().equals(assignment.getRole())
            ) {
                getRequest().ifPresent(request ->
                {
                    ApiUtil.setMessageResponse(request, "reservation already has an assignment with the given role");
                });
                return Optional.of(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
            }
        }

        try {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setEntityJsonResponse(request, assignments.save(assignment));
            });
        } catch (RuntimeException e) {
            return Optional.of(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        }

        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> personalAssignmentsIdPut(UUID id, Assignment assignment) {
        if (!id.equals(assignment.getId())) {
            if (!assignment.isIdSet()) {
                assignment.setId(id);
            } else {
                getRequest().ifPresent(request ->
                {
                    ApiUtil.setMessageResponse(request, "mismatching id in url and object");
                });
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        Optional<ResponseEntity> responseEntity = newAssignment(assignment);
        if (responseEntity.isPresent()) {
            return responseEntity.get();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsPost(Assignment assignment) {
        Assignment aFromDb = assignments.findById(assignment.getId()).orElse(null);
        Optional<ResponseEntity> responseEntity = newAssignment(assignment);
        if (responseEntity.isPresent()) {
            return responseEntity.get();
        }

        if (aFromDb == null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

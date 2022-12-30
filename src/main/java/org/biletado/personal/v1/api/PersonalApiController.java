package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.*;
import org.biletado.personal.v1.repository.AssignmentRepository;
import org.biletado.personal.v1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    ReservationsCaller reservationsCaller;
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
            getRequest().ifPresent(request ->
            {
                ApiUtil.setStringResponse(request, MediaType.TEXT_PLAIN_VALUE, "deletion not possible because of existing assignments");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // todo 401 if no (valid) authentication is given
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdPut(UUID id, Employee employee) {
        if (!id.equals(employee.getId())) {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setStringResponse(request, MediaType.TEXT_PLAIN_VALUE, "mismatching id in url and object");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Employee employeeFromDb = employees.findById(id).orElse(null);
        try {
            employees.save(employee);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (employeeFromDb == null) {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setEntityJsonResponse(request, employees.save(employee));
            });
            return new ResponseEntity<>(HttpStatus.CREATED);
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
            assignments.deleteById(id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
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

        // 422 if employee does not exist or the reservation does not exist
        if (employees.findById(assignment.getEmployeeId()).orElse(null) == null) {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setStringResponse(request, MediaType.TEXT_PLAIN_VALUE, "employee does not exist");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        // check reservations api if reservation exists
        try {
            Reservation reservation = reservationsCaller.getReservationsFromId(assignment.getReservationId());
        } catch (RuntimeException e) {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setStringResponse(request, MediaType.TEXT_PLAIN_VALUE, "reservation does not exist");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }


        // 422 if the reservation already has an assignment with the given role
        Iterable<Assignment> allAssignments = assignments.findAll();
        for (Assignment ass : allAssignments) {
            if (!(ass.getId().equals(assignment.getId())) && // check if the object is not just getting updated
                    ass.getReservationId().equals(assignment.getReservationId()) && ass.getRole().equals(assignment.getRole())
            ) {
                getRequest().ifPresent(request ->
                {
                    ApiUtil.setStringResponse(request, MediaType.TEXT_PLAIN_VALUE, "reservation already has an assignment with the given role");
                });
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        try {
            getRequest().ifPresent(request ->
            {
                ApiUtil.setEntityJsonResponse(request, assignments.save(assignment));
            });
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (aFromDb == null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.Assignment;
import org.biletado.personal.v1.model.Employee;
import org.biletado.personal.v1.model.PersonalAssignmentsGet200Response;
import org.biletado.personal.v1.model.PersonalEmployeesGet200Response;
import org.biletado.personal.v1.repository.AssignmentRepository;
import org.biletado.personal.v1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
@Controller
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
        Employee employeeOptional = employees.findById(id).orElse(null);
        if (employeeOptional == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        getRequest().ifPresent(request ->
        {
            ApiUtil.setEntityJsonResponse(request, employeeOptional);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdDelete(UUID id) {
        Employee employeeOptional = employees.findById(id).orElse(null);
        if (employeeOptional == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        employees.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
        // todo 401 if no (valid) authentication is given
        // todo 422 deletion not possible because of existing assignments
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdPut(UUID id, Employee employee) {
        return PersonalApi.super.personalEmployeesIdPut(id, employee);
    }

    @Override
    public ResponseEntity<Employee> personalEmployeesPost(Employee employee) {
        return PersonalApi.super.personalEmployeesPost(employee);
    }

    @Override
    public ResponseEntity<PersonalAssignmentsGet200Response> personalAssignmentsGet(UUID employeeId, UUID reservationId) {
        getRequest().ifPresent(request ->
        {
            Iterable<Assignment> assignmentIterable = assignments.findAll();
            ApiUtil.setEntityJsonResponse(request, assignmentIterable);
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
        return PersonalApi.super.personalAssignmentsIdDelete(id);
    }


    @Override
    public ResponseEntity<Void> personalAssignmentsIdPut(UUID id, Assignment assignment) {
        return PersonalApi.super.personalAssignmentsIdPut(id, assignment);
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsPost(Assignment assignment) {
        return PersonalApi.super.personalAssignmentsPost(assignment);
    }
}

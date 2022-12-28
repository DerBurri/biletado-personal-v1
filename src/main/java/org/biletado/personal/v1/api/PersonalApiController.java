package org.biletado.personal.v1.api;


import com.sun.tools.javac.util.DefinedBy;
import org.biletado.personal.v1.model.Employee;
import org.biletado.personal.v1.model.PersonalEmployeesGet200Response;
import org.biletado.personal.v1.model.PersonalStatusGet200Response;
import org.biletado.personal.v1.repository.AssignmentRepository;
import org.biletado.personal.v1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
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
    public ResponseEntity<PersonalStatusGet200Response> personalStatusGet() {
       Iterable<Employee> list =  employees.findAll();
        return PersonalApi.super.personalStatusGet();
    }

    @Override
    public ResponseEntity<PersonalEmployeesGet200Response> personalEmployeesGet()
    {
        Iterable<Employee> employeeIterable = employees.findAll();
        return PersonalApi.super.personalEmployeesGet();
    }
}

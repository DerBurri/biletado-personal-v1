package org.biletado.personal.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * PersonalEmployeesGet200Response
 */

@JsonTypeName("_personal_employees__get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
public class PersonalEmployeesGet200Response {

  @JsonProperty("employees")
  @Valid
  private List<Employee> employees = null;

  public PersonalEmployeesGet200Response employees(List<Employee> employees) {
    this.employees = employees;
    return this;
  }

  public PersonalEmployeesGet200Response addEmployeesItem(Employee employeesItem) {
    if (this.employees == null) {
      this.employees = new ArrayList<>();
    }
    this.employees.add(employeesItem);
    return this;
  }

  /**
   * list of all employees
   * @return employees
  */
  @Valid @Size(min = 0) 
  @Schema(name = "employees", description = "list of all employees", required = false)
  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalEmployeesGet200Response personalEmployeesGet200Response = (PersonalEmployeesGet200Response) o;
    return Objects.equals(this.employees, personalEmployeesGet200Response.employees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employees);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalEmployeesGet200Response {\n");
    sb.append("    employees: ").append(toIndentedString(employees)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


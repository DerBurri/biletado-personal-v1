package org.biletado.personal.v1.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;


import javax.annotation.Generated;

/**
 * the representation of an employee
 */

@Schema(name = "employee", description = "the representation of an employee")
@Entity
@Table(name = "employees")
@JsonTypeName("employee")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
public class Employee {

    private UUID id;

    @JsonIgnore
    @Transient
    private boolean idSet = false;

    private String name;

    public Employee() {
        this.id = UUID.randomUUID();
    }

    /**
     * the id of the employee
     *
     * @return id
     */
    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Valid
    @Schema(name = "id", description = "the id of the employee", required = false)
    public UUID getId() {
        return id;
    }


    /**
     * set the id of the employee
     *
     * @param id
     */
    public void setId(UUID id) {
        this.id = id;
        idSet = true;
    }

    @Transient
    public boolean isIdSet() {
        return idSet;
    }

    /**
     * the name of the employee
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", example = "Max Specimeno", description = "the name of the employee", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Employee {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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


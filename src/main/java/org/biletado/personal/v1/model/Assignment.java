package org.biletado.personal.v1.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Type;


import javax.annotation.Generated;

/**
 * the assignment between an employee and a reservation with its role. An assignment must only exist once per reservation and role.
 */


@Entity
@Table(name = "assignments")
@Schema(name = "assignment", description = "the assignment between an employee and a reservation with its role. An assignment must only exist once per reservation and role. ")
@JsonTypeName("assignment")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
public class Assignment {

    @JsonProperty("id")
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @JsonProperty("employee_id")
    @Column(name = "employee_id")
    private UUID employeeId;


    @JsonProperty("reservation_id")
    @Column(name = "reservation_id")
    private UUID reservationId;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;


    /**
     * the role which the employee impersonates in this assignment
     */
    public enum RoleEnum {
        service("service"),

        cleanup("cleanup");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }

        @JsonCreator
        public static RoleEnum fromValue(String value) {
            for (RoleEnum b : RoleEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("role")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Type(type = "org.biletado.personal.v1.model.PostgreSQLEnumType")
    private RoleEnum role;


//    public Assignment id(UUID id) {
//        this.id = id;
//        return this;
//    }

    /**
     * the id of the assignment
     *
     * @return id
     */
    @Valid
    @Schema(name = "id", description = "the id of the assignment", required = false)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    public Assignment employeeId(UUID employeeId) {
//        this.employee.id(employeeId);
//        return this;
//    }

    /**
     * the id of the employee this assignment references
     *
     * @return employeeId
     */
    @NotNull
    @Valid
    @Schema(name = "employee_id", description = "the id of the employee this assignment references", required = true)
    public UUID getEmployeeId() {
        return employeeId;
//        return employee.getId();
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
//        employee.setId(employeeId);
    }

//    public Assignment reservationId(UUID reservationId) {
//        this.reservationId = reservationId;
//        return this;
//    }

    /**
     * the id of the reservation this assignment references
     *
     * @return reservationId
     */
    @NotNull
    @Valid
    @Schema(name = "reservation_id", description = "the id of the reservation this assignment references", required = true)
    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

//    public Assignment role(RoleEnum role) {
//        this.role = role;
//        return this;
//    }

    /**
     * the role which the employee impersonates in this assignment
     *
     * @return role
     */
    @NotNull
    @Schema(name = "role", description = "the role which the employee impersonates in this assignment", required = true)
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assignment assignment = (Assignment) o;
        return Objects.equals(this.id, assignment.id) &&
                Objects.equals(employeeId, assignment.getEmployeeId()) &&
                Objects.equals(this.reservationId, assignment.reservationId) &&
                Objects.equals(this.role, assignment.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, reservationId, role);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Assignment {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    employeeId: ").append(toIndentedString(employeeId)).append("\n");
        sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
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


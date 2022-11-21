package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.Assignment;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PersonalAssignmentsGet200Response
 */

@JsonTypeName("_personal_assignments__get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-21T18:24:00.468964Z[Etc/UTC]")
public class PersonalAssignmentsGet200Response {

  @JsonProperty("assignments")
  @Valid
  private List<Assignment> assignments = null;

  public PersonalAssignmentsGet200Response assignments(List<Assignment> assignments) {
    this.assignments = assignments;
    return this;
  }

  public PersonalAssignmentsGet200Response addAssignmentsItem(Assignment assignmentsItem) {
    if (this.assignments == null) {
      this.assignments = new ArrayList<>();
    }
    this.assignments.add(assignmentsItem);
    return this;
  }

  /**
   * Get assignments
   * @return assignments
  */
  @Valid @Size(min = 0) 
  @Schema(name = "assignments", required = false)
  public List<Assignment> getAssignments() {
    return assignments;
  }

  public void setAssignments(List<Assignment> assignments) {
    this.assignments = assignments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalAssignmentsGet200Response personalAssignmentsGet200Response = (PersonalAssignmentsGet200Response) o;
    return Objects.equals(this.assignments, personalAssignmentsGet200Response.assignments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assignments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalAssignmentsGet200Response {\n");
    sb.append("    assignments: ").append(toIndentedString(assignments)).append("\n");
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


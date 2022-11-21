package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PersonalStatusGet200Response
 */

@JsonTypeName("_personal_status__get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-21T18:24:00.468964Z[Etc/UTC]")
public class PersonalStatusGet200Response extends HashMap<String, Object> {

  @JsonProperty("authors")
  @Valid
  private List<String> authors = null;

  public PersonalStatusGet200Response authors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public PersonalStatusGet200Response addAuthorsItem(String authorsItem) {
    if (this.authors == null) {
      this.authors = new ArrayList<>();
    }
    this.authors.add(authorsItem);
    return this;
  }

  /**
   * Get authors
   * @return authors
  */
  @Size(min = 1) 
  @Schema(name = "authors", required = false)
  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalStatusGet200Response personalStatusGet200Response = (PersonalStatusGet200Response) o;
    return Objects.equals(this.authors, personalStatusGet200Response.authors) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authors, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalStatusGet200Response {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
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


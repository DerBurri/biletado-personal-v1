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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-22T12:08:12.414629Z[Etc/UTC]")
public class PersonalStatusGet200Response extends HashMap<String, Object> {

  @JsonProperty("authors")
  @Valid
  private List<String> authors = null;

  @JsonProperty("apiVersion")
  private String apiVersion;

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
   * List of all authors of this backend.
   * @return authors
  */
  @Size(min = 1) 
  @Schema(name = "authors", description = "List of all authors of this backend.", required = false)
  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public PersonalStatusGet200Response apiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
    return this;
  }

  /**
   * The exact version of the api implemented by this backend.
   * @return apiVersion
  */
  @Pattern(regexp = "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$") 
  @Schema(name = "apiVersion", example = "1.2.3", description = "The exact version of the api implemented by this backend.", required = false)
  public String getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
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
        Objects.equals(this.apiVersion, personalStatusGet200Response.apiVersion) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authors, apiVersion, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalStatusGet200Response {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
    sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
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


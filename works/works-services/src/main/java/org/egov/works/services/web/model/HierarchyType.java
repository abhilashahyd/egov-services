package org.egov.works.services.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * HierarchyType
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-25T07:37:26.972Z")

public class HierarchyType   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("loalname")
  private String loalname = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  public HierarchyType id(String id) {
    this.id = id;
    return this;
  }

   /**
   * unique id for the HierarchyType.
   * @return id
  **/
  @ApiModelProperty(value = "unique id for the HierarchyType.")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public HierarchyType code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Unique Code for HierarchyType.
   * @return code
  **/
  @ApiModelProperty(required = true, value = "Unique Code for HierarchyType.")
  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public HierarchyType name(String name) {
    this.name = name;
    return this;
  }

   /**
   * HierarchyType Name.
   * @return name
  **/
  @ApiModelProperty(required = true, value = "HierarchyType Name.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HierarchyType loalname(String loalname) {
    this.loalname = loalname;
    return this;
  }

   /**
   * Local HierarchyType name
   * @return loalname
  **/
  @ApiModelProperty(value = "Local HierarchyType name")


  public String getLoalname() {
    return loalname;
  }

  public void setLoalname(String loalname) {
    this.loalname = loalname;
  }

  public HierarchyType tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

   /**
   * Unique TenantId for HierarchyType .
   * @return tenantId
  **/
  @ApiModelProperty(required = true, value = "Unique TenantId for HierarchyType .")
  @NotNull


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HierarchyType hierarchyType = (HierarchyType) o;
    return Objects.equals(this.id, hierarchyType.id) &&
        Objects.equals(this.code, hierarchyType.code) &&
        Objects.equals(this.name, hierarchyType.name) &&
        Objects.equals(this.loalname, hierarchyType.loalname) &&
        Objects.equals(this.tenantId, hierarchyType.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, name, loalname, tenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HierarchyType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    loalname: ").append(toIndentedString(loalname)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
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


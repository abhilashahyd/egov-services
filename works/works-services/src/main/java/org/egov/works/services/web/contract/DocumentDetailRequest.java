package org.egov.works.services.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.egov.works.services.web.model.DocumentDetail;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contract class to send response. Array of Document Detail items are used in case of search results, also multiple  Document Detail item is used for create and update
 */
@ApiModel(description = "Contract class to send response. Array of Document Detail items are used in case of search results, also multiple  Document Detail item is used for create and update")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-25T07:37:26.972Z")

public class DocumentDetailRequest   {
  @JsonProperty("requestInfo")
  private RequestInfo requestInfo = null;

  @JsonProperty("documentDetails")
  private List<DocumentDetail> documentDetails = null;

  public DocumentDetailRequest requestInfo(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
    return this;
  }

   /**
   * Get requestInfo
   * @return requestInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }

  public void setRequestInfo(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
  }

  public DocumentDetailRequest documentDetails(List<DocumentDetail> documentDetails) {
    this.documentDetails = documentDetails;
    return this;
  }

  public DocumentDetailRequest addDocumentDetailsItem(DocumentDetail documentDetailsItem) {
    if (this.documentDetails == null) {
      this.documentDetails = new ArrayList<DocumentDetail>();
    }
    this.documentDetails.add(documentDetailsItem);
    return this;
  }

   /**
   * Used for create and update only
   * @return documentDetails
  **/
  @ApiModelProperty(value = "Used for create and update only")

  @Valid

  public List<DocumentDetail> getDocumentDetails() {
    return documentDetails;
  }

  public void setDocumentDetails(List<DocumentDetail> documentDetails) {
    this.documentDetails = documentDetails;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentDetailRequest documentDetailRequest = (DocumentDetailRequest) o;
    return Objects.equals(this.requestInfo, documentDetailRequest.requestInfo) &&
        Objects.equals(this.documentDetails, documentDetailRequest.documentDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestInfo, documentDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentDetailRequest {\n");
    
    sb.append("    requestInfo: ").append(toIndentedString(requestInfo)).append("\n");
    sb.append("    documentDetails: ").append(toIndentedString(documentDetails)).append("\n");
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


/*
 * Sourcify APIv2
 * Welcome to the Sourcify's APIv2.  Important differences between the deprecated legacy API and the new APIv2: - **Ticketing**: The verfication requests resolve into tickets/verification jobs.    - Previously the verification happened during the HTTP request, which resulted in timeouts if compilation took longer - **Standard JSON as default**: In the current design we take the standard JSON format as our main verification endpoint. We still support verification with metadata at `/v2/verify/metadata`. - **Lean API**: We keep the number of endpoints minimal compared to v1. We won't have a session API.  - **Detailed contract response**: Prev. we only returned contract files of a contract. Now we can return details at `/contract/{chainId}/{address}`.
 *
 * The version of the OpenAPI document: 2.0.0
 * Contact: hello@sourcify.dev
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ru.javaboys.defidog.integrations.sourcify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * CreationTransformationValues1
 */
@JsonPropertyOrder({
  CreationTransformationValues1.JSON_PROPERTY_LIBRARIES,
  CreationTransformationValues1.JSON_PROPERTY_IMMUTABLES,
  CreationTransformationValues1.JSON_PROPERTY_CBOR_AUXDATA,
  CreationTransformationValues1.JSON_PROPERTY_CALL_PROTECTION
})
@JsonTypeName("CreationTransformationValues_1")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-08T01:55:37.009934+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class CreationTransformationValues1 {
  public static final String JSON_PROPERTY_LIBRARIES = "libraries";
  private Map<String, String> libraries = new HashMap<>();

  public static final String JSON_PROPERTY_IMMUTABLES = "immutables";
  private Map<String, String> immutables = new HashMap<>();

  public static final String JSON_PROPERTY_CBOR_AUXDATA = "cborAuxdata";
  private Map<String, String> cborAuxdata = new HashMap<>();

  public static final String JSON_PROPERTY_CALL_PROTECTION = "callProtection";
  private String callProtection;

  public CreationTransformationValues1() {
  }

  public CreationTransformationValues1 libraries(Map<String, String> libraries) {
    
    this.libraries = libraries;
    return this;
  }

  public CreationTransformationValues1 putLibrariesItem(String key, String librariesItem) {
    if (this.libraries == null) {
      this.libraries = new HashMap<>();
    }
    this.libraries.put(key, librariesItem);
    return this;
  }

  /**
   * Get libraries
   * @return libraries
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LIBRARIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getLibraries() {
    return libraries;
  }


  @JsonProperty(JSON_PROPERTY_LIBRARIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLibraries(Map<String, String> libraries) {
    this.libraries = libraries;
  }

  public CreationTransformationValues1 immutables(Map<String, String> immutables) {
    
    this.immutables = immutables;
    return this;
  }

  public CreationTransformationValues1 putImmutablesItem(String key, String immutablesItem) {
    if (this.immutables == null) {
      this.immutables = new HashMap<>();
    }
    this.immutables.put(key, immutablesItem);
    return this;
  }

  /**
   * Get immutables
   * @return immutables
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_IMMUTABLES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getImmutables() {
    return immutables;
  }


  @JsonProperty(JSON_PROPERTY_IMMUTABLES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setImmutables(Map<String, String> immutables) {
    this.immutables = immutables;
  }

  public CreationTransformationValues1 cborAuxdata(Map<String, String> cborAuxdata) {
    
    this.cborAuxdata = cborAuxdata;
    return this;
  }

  public CreationTransformationValues1 putCborAuxdataItem(String key, String cborAuxdataItem) {
    if (this.cborAuxdata == null) {
      this.cborAuxdata = new HashMap<>();
    }
    this.cborAuxdata.put(key, cborAuxdataItem);
    return this;
  }

  /**
   * Different format than the &#x60;cborAuxdata&#x60; field under the bytecode object.   Will be here only if there was a transformation on the cborAuxdata. If cborAuxdatas matched, there won&#39;t be a transformation.
   * @return cborAuxdata
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CBOR_AUXDATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getCborAuxdata() {
    return cborAuxdata;
  }


  @JsonProperty(JSON_PROPERTY_CBOR_AUXDATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCborAuxdata(Map<String, String> cborAuxdata) {
    this.cborAuxdata = cborAuxdata;
  }

  public CreationTransformationValues1 callProtection(String callProtection) {
    
    this.callProtection = callProtection;
    return this;
  }

  /**
   * Contract Address in hex string
   * @return callProtection
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CALL_PROTECTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCallProtection() {
    return callProtection;
  }


  @JsonProperty(JSON_PROPERTY_CALL_PROTECTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCallProtection(String callProtection) {
    this.callProtection = callProtection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreationTransformationValues1 creationTransformationValues1 = (CreationTransformationValues1) o;
    return Objects.equals(this.libraries, creationTransformationValues1.libraries) &&
        Objects.equals(this.immutables, creationTransformationValues1.immutables) &&
        Objects.equals(this.cborAuxdata, creationTransformationValues1.cborAuxdata) &&
        Objects.equals(this.callProtection, creationTransformationValues1.callProtection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(libraries, immutables, cborAuxdata, callProtection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreationTransformationValues1 {\n");
    sb.append("    libraries: ").append(toIndentedString(libraries)).append("\n");
    sb.append("    immutables: ").append(toIndentedString(immutables)).append("\n");
    sb.append("    cborAuxdata: ").append(toIndentedString(cborAuxdata)).append("\n");
    sb.append("    callProtection: ").append(toIndentedString(callProtection)).append("\n");
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


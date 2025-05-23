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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * RuntimeBytecodeResponse
 */
@JsonPropertyOrder({
  RuntimeBytecodeResponse.JSON_PROPERTY_ONCHAIN_BYTECODE,
  RuntimeBytecodeResponse.JSON_PROPERTY_RECOMPILED_BYTECODE,
  RuntimeBytecodeResponse.JSON_PROPERTY_SOURCE_MAP,
  RuntimeBytecodeResponse.JSON_PROPERTY_LINK_REFERENCES,
  RuntimeBytecodeResponse.JSON_PROPERTY_CBOR_AUXDATA,
  RuntimeBytecodeResponse.JSON_PROPERTY_IMMUTABLE_REFERENCES,
  RuntimeBytecodeResponse.JSON_PROPERTY_TRANSFORMATIONS,
  RuntimeBytecodeResponse.JSON_PROPERTY_TRANSFORMATION_VALUES
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-08T01:55:37.009934+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class RuntimeBytecodeResponse {
  public static final String JSON_PROPERTY_ONCHAIN_BYTECODE = "onchainBytecode";
  private String onchainBytecode;

  public static final String JSON_PROPERTY_RECOMPILED_BYTECODE = "recompiledBytecode";
  private String recompiledBytecode;

  public static final String JSON_PROPERTY_SOURCE_MAP = "sourceMap";
  private String sourceMap;

  public static final String JSON_PROPERTY_LINK_REFERENCES = "linkReferences";
  private LinkReferences linkReferences;

  public static final String JSON_PROPERTY_CBOR_AUXDATA = "cborAuxdata";
  private Map<String, CborAuxdataObject> cborAuxdata = new HashMap<>();

  public static final String JSON_PROPERTY_IMMUTABLE_REFERENCES = "immutableReferences";
  private Map<String, List<ImmutableReference>> immutableReferences = new HashMap<>();

  public static final String JSON_PROPERTY_TRANSFORMATIONS = "transformations";
  private List<RuntimeBytecodeResponseTransformationsInner> transformations = new ArrayList<>();

  public static final String JSON_PROPERTY_TRANSFORMATION_VALUES = "transformationValues";
  private CreationTransformationValues1 transformationValues;

  public RuntimeBytecodeResponse() {
  }

  public RuntimeBytecodeResponse onchainBytecode(String onchainBytecode) {
    
    this.onchainBytecode = onchainBytecode;
    return this;
  }

  /**
   * Get onchainBytecode
   * @return onchainBytecode
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ONCHAIN_BYTECODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOnchainBytecode() {
    return onchainBytecode;
  }


  @JsonProperty(JSON_PROPERTY_ONCHAIN_BYTECODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOnchainBytecode(String onchainBytecode) {
    this.onchainBytecode = onchainBytecode;
  }

  public RuntimeBytecodeResponse recompiledBytecode(String recompiledBytecode) {
    
    this.recompiledBytecode = recompiledBytecode;
    return this;
  }

  /**
   * Get recompiledBytecode
   * @return recompiledBytecode
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_RECOMPILED_BYTECODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRecompiledBytecode() {
    return recompiledBytecode;
  }


  @JsonProperty(JSON_PROPERTY_RECOMPILED_BYTECODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRecompiledBytecode(String recompiledBytecode) {
    this.recompiledBytecode = recompiledBytecode;
  }

  public RuntimeBytecodeResponse sourceMap(String sourceMap) {
    
    this.sourceMap = sourceMap;
    return this;
  }

  /**
   * Get sourceMap
   * @return sourceMap
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SOURCE_MAP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSourceMap() {
    return sourceMap;
  }


  @JsonProperty(JSON_PROPERTY_SOURCE_MAP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSourceMap(String sourceMap) {
    this.sourceMap = sourceMap;
  }

  public RuntimeBytecodeResponse linkReferences(LinkReferences linkReferences) {
    
    this.linkReferences = linkReferences;
    return this;
  }

  /**
   * Get linkReferences
   * @return linkReferences
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LINK_REFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public LinkReferences getLinkReferences() {
    return linkReferences;
  }


  @JsonProperty(JSON_PROPERTY_LINK_REFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLinkReferences(LinkReferences linkReferences) {
    this.linkReferences = linkReferences;
  }

  public RuntimeBytecodeResponse cborAuxdata(Map<String, CborAuxdataObject> cborAuxdata) {
    
    this.cborAuxdata = cborAuxdata;
    return this;
  }

  public RuntimeBytecodeResponse putCborAuxdataItem(String key, CborAuxdataObject cborAuxdataItem) {
    if (this.cborAuxdata == null) {
      this.cborAuxdata = new HashMap<>();
    }
    this.cborAuxdata.put(key, cborAuxdataItem);
    return this;
  }

  /**
   * Get cborAuxdata
   * @return cborAuxdata
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CBOR_AUXDATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, CborAuxdataObject> getCborAuxdata() {
    return cborAuxdata;
  }


  @JsonProperty(JSON_PROPERTY_CBOR_AUXDATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCborAuxdata(Map<String, CborAuxdataObject> cborAuxdata) {
    this.cborAuxdata = cborAuxdata;
  }

  public RuntimeBytecodeResponse immutableReferences(Map<String, List<ImmutableReference>> immutableReferences) {
    
    this.immutableReferences = immutableReferences;
    return this;
  }

  public RuntimeBytecodeResponse putImmutableReferencesItem(String key, List<ImmutableReference> immutableReferencesItem) {
    if (this.immutableReferences == null) {
      this.immutableReferences = new HashMap<>();
    }
    this.immutableReferences.put(key, immutableReferencesItem);
    return this;
  }

  /**
   * Get immutableReferences
   * @return immutableReferences
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_IMMUTABLE_REFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, List<ImmutableReference>> getImmutableReferences() {
    return immutableReferences;
  }


  @JsonProperty(JSON_PROPERTY_IMMUTABLE_REFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setImmutableReferences(Map<String, List<ImmutableReference>> immutableReferences) {
    this.immutableReferences = immutableReferences;
  }

  public RuntimeBytecodeResponse transformations(List<RuntimeBytecodeResponseTransformationsInner> transformations) {
    
    this.transformations = transformations;
    return this;
  }

  public RuntimeBytecodeResponse addTransformationsItem(RuntimeBytecodeResponseTransformationsInner transformationsItem) {
    if (this.transformations == null) {
      this.transformations = new ArrayList<>();
    }
    this.transformations.add(transformationsItem);
    return this;
  }

  /**
   * Get transformations
   * @return transformations
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TRANSFORMATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<RuntimeBytecodeResponseTransformationsInner> getTransformations() {
    return transformations;
  }


  @JsonProperty(JSON_PROPERTY_TRANSFORMATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTransformations(List<RuntimeBytecodeResponseTransformationsInner> transformations) {
    this.transformations = transformations;
  }

  public RuntimeBytecodeResponse transformationValues(CreationTransformationValues1 transformationValues) {
    
    this.transformationValues = transformationValues;
    return this;
  }

  /**
   * Get transformationValues
   * @return transformationValues
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TRANSFORMATION_VALUES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public CreationTransformationValues1 getTransformationValues() {
    return transformationValues;
  }


  @JsonProperty(JSON_PROPERTY_TRANSFORMATION_VALUES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTransformationValues(CreationTransformationValues1 transformationValues) {
    this.transformationValues = transformationValues;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RuntimeBytecodeResponse runtimeBytecodeResponse = (RuntimeBytecodeResponse) o;
    return Objects.equals(this.onchainBytecode, runtimeBytecodeResponse.onchainBytecode) &&
        Objects.equals(this.recompiledBytecode, runtimeBytecodeResponse.recompiledBytecode) &&
        Objects.equals(this.sourceMap, runtimeBytecodeResponse.sourceMap) &&
        Objects.equals(this.linkReferences, runtimeBytecodeResponse.linkReferences) &&
        Objects.equals(this.cborAuxdata, runtimeBytecodeResponse.cborAuxdata) &&
        Objects.equals(this.immutableReferences, runtimeBytecodeResponse.immutableReferences) &&
        Objects.equals(this.transformations, runtimeBytecodeResponse.transformations) &&
        Objects.equals(this.transformationValues, runtimeBytecodeResponse.transformationValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(onchainBytecode, recompiledBytecode, sourceMap, linkReferences, cborAuxdata, immutableReferences, transformations, transformationValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RuntimeBytecodeResponse {\n");
    sb.append("    onchainBytecode: ").append(toIndentedString(onchainBytecode)).append("\n");
    sb.append("    recompiledBytecode: ").append(toIndentedString(recompiledBytecode)).append("\n");
    sb.append("    sourceMap: ").append(toIndentedString(sourceMap)).append("\n");
    sb.append("    linkReferences: ").append(toIndentedString(linkReferences)).append("\n");
    sb.append("    cborAuxdata: ").append(toIndentedString(cborAuxdata)).append("\n");
    sb.append("    immutableReferences: ").append(toIndentedString(immutableReferences)).append("\n");
    sb.append("    transformations: ").append(toIndentedString(transformations)).append("\n");
    sb.append("    transformationValues: ").append(toIndentedString(transformationValues)).append("\n");
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


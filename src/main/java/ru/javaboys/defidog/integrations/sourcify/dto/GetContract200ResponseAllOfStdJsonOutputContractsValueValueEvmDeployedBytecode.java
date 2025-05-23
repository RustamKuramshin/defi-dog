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
 * In Sourcify we refer to this field more explicitly as \&quot;runtime bytecode\&quot;
 */
@JsonPropertyOrder({
  GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.JSON_PROPERTY_SOURCE_MAP,
  GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.JSON_PROPERTY_LINK_REFERENCES,
  GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.JSON_PROPERTY_OBJECT,
  GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.JSON_PROPERTY_IMMUTABLE_REFERENCES
})
@JsonTypeName("get_contract_200_response_allOf_stdJsonOutput_contracts_value_value_evm_deployedBytecode")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-08T01:55:37.009934+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode {
  public static final String JSON_PROPERTY_SOURCE_MAP = "sourceMap";
  private String sourceMap;

  public static final String JSON_PROPERTY_LINK_REFERENCES = "linkReferences";
  private LinkReferences linkReferences;

  public static final String JSON_PROPERTY_OBJECT = "object";
  private String _object;

  public static final String JSON_PROPERTY_IMMUTABLE_REFERENCES = "immutableReferences";
  private Map<String, Object> immutableReferences = new HashMap<>();

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode() {
  }

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode sourceMap(String sourceMap) {
    
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

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode linkReferences(LinkReferences linkReferences) {
    
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

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode _object(String _object) {
    
    this._object = _object;
    return this;
  }

  /**
   * Get _object
   * @return _object
   */
  @jakarta.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OBJECT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getObject() {
    return _object;
  }


  @JsonProperty(JSON_PROPERTY_OBJECT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setObject(String _object) {
    this._object = _object;
  }

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode immutableReferences(Map<String, Object> immutableReferences) {
    
    this.immutableReferences = immutableReferences;
    return this;
  }

  public GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode putImmutableReferencesItem(String key, Object immutableReferencesItem) {
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

  public Map<String, Object> getImmutableReferences() {
    return immutableReferences;
  }


  @JsonProperty(JSON_PROPERTY_IMMUTABLE_REFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setImmutableReferences(Map<String, Object> immutableReferences) {
    this.immutableReferences = immutableReferences;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode getContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode = (GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode) o;
    return Objects.equals(this.sourceMap, getContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.sourceMap) &&
        Objects.equals(this.linkReferences, getContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.linkReferences) &&
        Objects.equals(this._object, getContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode._object) &&
        Objects.equals(this.immutableReferences, getContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode.immutableReferences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceMap, linkReferences, _object, immutableReferences);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetContract200ResponseAllOfStdJsonOutputContractsValueValueEvmDeployedBytecode {\n");
    sb.append("    sourceMap: ").append(toIndentedString(sourceMap)).append("\n");
    sb.append("    linkReferences: ").append(toIndentedString(linkReferences)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    immutableReferences: ").append(toIndentedString(immutableReferences)).append("\n");
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


package es.gva.dgtic.jira.issue;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Example class maps to Issue in JIRA
 * @author shashi
 *
 */
@Generated("org.jsonschema2pojo")
public class Example {

  @SerializedName("id")
  @Expose
  private String id;
  
  @SerializedName("key")
  @Expose
  private String key;
  
  @SerializedName("fields")
  @Expose
  private Fields fields;
  

  /**
   * 
   * @return The id
   */
  public String getId() {
    return id;
  }

  /**
   * 
   * @param id
   *          The id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * 
   * @return The key
   */
  public String getKey() {
    return key;
  }

  /**
   * 
   * @param key
   *          The key
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * 
   * @return The fields
   */
  public Fields getFields() {
    return fields;
  }

  /**
   * 
   * @param fields
   *          The fields
   */
  public void setFields(Fields fields) {
    this.fields = fields;
  }

}

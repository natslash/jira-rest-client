package es.gva.dgtic.jira.issue;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Attachments list in the issue
 * @author shashi
 *
 */
@Generated("org.jsonschema2pojo")
public class Attachment {

  @SerializedName("id")
  @Expose
  private String id;
  
  @SerializedName("filename")
  @Expose
  private String filename;  
  
  @SerializedName("size")
  @Expose
  private Integer size;
  
  @SerializedName("mimeType")
  @Expose
  private String mimeType;
  
  @SerializedName("content")
  @Expose
  private String content;

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
   * @return The filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * 
   * @param filename
   *          The filename
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * 
   * @return The size
   */
  public Integer getSize() {
    return size;
  }

  /**
   * 
   * @param size
   *          The size
   */
  public void setSize(Integer size) {
    this.size = size;
  }

  /**
   * 
   * @return The mimeType
   */
  public String getMimeType() {
    return mimeType;
  }

  /**
   * 
   * @param mimeType
   *          The mimeType
   */
  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  /**
   * 
   * @return The content
   */
  public String getContent() {
    return content;
  }

  /**
   * 
   * @param content
   *          The content
   */
  public void setContent(String content) {
    this.content = content;
  }
}

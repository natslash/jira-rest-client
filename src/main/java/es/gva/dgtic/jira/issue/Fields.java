package es.gva.dgtic.jira.issue;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Fields in JIRA issue
 * 
 * @author shashi
 *
 */
@Generated("org.jsonschema2pojo")
public class Fields {

  @SerializedName("attachment")
  @Expose
  @Valid
  private List<Attachment> attachment = new ArrayList<>();

  /**
   * No args constructor for use in serialization
   * 
   */
  public Fields() {
    // No args constructor for serialization
  }

  /**
   * 
   * @param attachment
   * 
   */
  public Fields(List<Attachment> attachment) {

    setAttachment(attachment);

  }

  /**
   * 
   * @return The attachment
   */
  public List<Attachment> getAttachment() {
    return attachment;
  }

  /**
   * 
   * @param attachment
   *          The attachment
   */
  public void setAttachment(List<Attachment> attachment) {
    this.attachment = attachment;
  }

}

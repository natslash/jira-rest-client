package es.gva.dgtic.jira.issue;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Fields in JIRA issue
 * @author shashi
 *
 */
@Generated("org.jsonschema2pojo")
public class Fields {

  @SerializedName("attachment")
  @Expose
  private List<Attachment> attachment = new ArrayList<>();

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

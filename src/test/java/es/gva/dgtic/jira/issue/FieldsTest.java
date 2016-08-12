package es.gva.dgtic.jira.issue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;




public class FieldsTest {

  @Test
  public void test() {
    List<Attachment> attList = new ArrayList<>();
    Attachment att = new Attachment("1", "MyFileName", 1000, "MimeType", "Content");
    attList.add(att);
    
    Fields fields = new Fields(attList);
    
    assertNotNull("Attachment is null", fields.getAttachment());
    assertNotNull("toString is null", fields.toString());
  }

}

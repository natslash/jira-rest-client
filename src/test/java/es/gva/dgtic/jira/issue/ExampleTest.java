package es.gva.dgtic.jira.issue;

import static org.junit.Assert.*;

import org.junit.Test;


public class ExampleTest {

  @Test
  public void test() {
    Example issue = new Example();
    Fields fields = new Fields();
    
    issue.setFields(fields);
    issue.setId("1");
    issue.setKey("issueKey");
    
    assertNotNull("fields is null", issue.getFields());
    assertTrue("Id is not 1", issue.getId().equals("1"));
    assertTrue("key is not issueKey", issue.getKey().equals("issueKey"));
  }

}

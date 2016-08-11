package es.gva.dgtic.jira.issue;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class AttachmentTest {

  @Test
  public void testAttachment() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
    String jsonString = "{\"expand\":\"renderedFields,names,schema,transitions,operations,editmeta,changelog,versionedRepresentations\",\"id\":\"42921\",\"self\":\"https://jira.excentia.es/rest/api/2/issue/42921\",\"key\":\"OWASP-254\",\"fields\":{\"attachment\":[{\"self\":\"https://jira.excentia.es/rest/api/2/attachment/41512\",\"id\":\"41512\",\"filename\":\"sonar-owasp-plugin-2..3.2.1-SNAPSHOT.jar\",\"author\":{\"self\":\"https://jira.excentia.es/rest/api/2/user?username=ssubramanyam\",\"name\":\"ssubramanyam\",\"key\":\"ssubramanyam\",\"emailAddress\":\"ssubramanyam@excentia.es\",\"avatarUrls\":{\"48x48\":\"https://jira.excentia.es/secure/useravatar?ownerId=ssubramanyam&avatarId=13611\",\"24x24\":\"https://jira.excentia.es/secure/useravatar?size=small&ownerId=ssubramanyam&avatarId=13611\",\"16x16\":\"https://jira.excentia.es/secure/useravatar?size=xsmall&ownerId=ssubramanyam&avatarId=13611\",\"32x32\":\"https://jira.excentia.es/secure/useravatar?size=medium&ownerId=ssubramanyam&avatarId=13611\"},\"displayName\":\"Shashidhara Subramanyam\",\"active\":true,\"timeZone\":\"Europe/Madrid\"},\"created\":\"2016-08-04T09:13:58.000+0200\",\"size\":1190250,\"mimeType\":\"application/x-java-archive\",\"content\":\"https://jira.excentia.es/secure/attachment/41512/sonar-owasp-plugin-2..3.2.1-SNAPSHOT.jar\"}]}}";
    
    JsonParser parser = new JsonParser();

    // Create JSON object from the given string
    JsonObject jsonObject = (JsonObject) parser.parse(jsonString);
    JsonObject jData = jsonObject.getAsJsonObject("fields");
    
    Gson gson = new Gson();
    Fields fields = gson.fromJson(jData, Fields.class);
    List<Attachment> attList = fields.getAttachment();
    System.out.println(attList.size());
    assertTrue(attList.size() > 0);
  }

}
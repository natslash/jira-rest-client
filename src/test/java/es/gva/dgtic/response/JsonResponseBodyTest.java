package es.gva.dgtic.response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonResponseBodyTest {

  String jsonString = "{\"expand\": \"renderedFields,names,schema,transitions,operations,editmeta,changelog\", \"id\": \"339820\",  \"self\": \"http://jira.gva.es/rest/api/2/issue/339820\",  \"key\": \"CALTIC-5749\",  \"fields\": {    \"attachment\": [      {        \"self\": \"http://jira.gva.es/rest/api/2/attachment/135370\",        \"id\": \"135370\",        \"filename\": \"Frontal_parent_pom.xml\",        \"author\": {          \"self\": \"http://jira.gva.es/rest/api/2/user?username=subramanyam_sha\",          \"name\": \"subramanyam_sha\",          \"key\": \"subramanyam_sha\",          \"emailAddress\": \"subramanyam_sha@externos.gva.es\",          \"avatarUrls\": {            \"48x48\": \"http://jira.gva.es/secure/useravatar?avatarId=10122\",            \"24x24\": \"http://jira.gva.es/secure/useravatar?size=small&avatarId=10122\",            \"16x16\": \"http://jira.gva.es/secure/useravatar?size=xsmall&avatarId=10122\",            \"32x32\": \"http://jira.gva.es/secure/useravatar?size=medium&avatarId=10122\"          },          \"displayName\": \"Shashidhara Subramanyam\",          \"active\": true,          \"timeZone\": \"Europe/Madrid\"        },        \"created\": \"2016-08-05T08:30:06.000+0200\",        \"size\": 964,        \"mimeType\": \"text/xml\",        \"content\": \"http://jira.gva.es/secure/attachment/135370/Frontal_parent_pom.xml\"      }    ]  }}";

  @Test
  public void testGetResponseObject() {

    @SuppressWarnings("unchecked")
    ResponseEntity<String> response = mock(ResponseEntity.class);
    HttpHeaders header = mock(HttpHeaders.class);
    when(response.getBody()).thenReturn(jsonString);
    when(response.getHeaders()).thenReturn(header);
    when(header.getContentType()).thenReturn(MediaType.APPLICATION_JSON_UTF8);
    JsonResponseBody jresp = new JsonResponseBody(response);
    JsonObject obj = jresp.getResponseBody();
    JsonObject jData = obj.getAsJsonObject("fields");
    JsonArray attArray = jData.getAsJsonArray("attachment");
    assertNotNull("Data is null", jData);
    assertNotNull("Attachment array is null", attArray);
  }

  @Test
  public void testUnsupportedResponse() {
    @SuppressWarnings("unchecked")
    ResponseEntity<String> response = mock(ResponseEntity.class);
    HttpHeaders header = mock(HttpHeaders.class);
    when(response.getBody()).thenReturn(jsonString);
    when(response.getHeaders()).thenReturn(header);
    when(header.getContentType()).thenReturn(MediaType.APPLICATION_OCTET_STREAM);
    JsonResponseBody jresp = new JsonResponseBody(response);
    assertNull("Response is not null", jresp.getResponseBody());
  }

}

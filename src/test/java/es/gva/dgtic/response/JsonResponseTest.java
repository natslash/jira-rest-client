package es.gva.dgtic.response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class JsonResponseTest {

  @Test
  public void testGetResponseObject() {
    String jsonString = "{\"Success\":true,\"Message\":\"Invalid access token.\"}";
    @SuppressWarnings("unchecked")
    ResponseEntity<String> response = mock(ResponseEntity.class);
    HttpHeaders header = mock(HttpHeaders.class);
    when(response.getBody()).thenReturn(jsonString);
    when(response.getHeaders()).thenReturn(header);
    when(header.getContentType()).thenReturn(MediaType.APPLICATION_JSON_UTF8);
    JsonResponse jresp = new JsonResponse();
    assertNotNull(jresp.getResponseObject(response));
  }
  
  @Test
  public void testUnsupportedResponse() {
    String jsonString = "{\"Success\":true,\"Message\":\"Invalid access token.\"}";
    @SuppressWarnings("unchecked")
    ResponseEntity<String> response = mock(ResponseEntity.class);
    HttpHeaders header = mock(HttpHeaders.class);
    when(response.getBody()).thenReturn(jsonString);
    when(response.getHeaders()).thenReturn(header);
    when(header.getContentType()).thenReturn(MediaType.APPLICATION_OCTET_STREAM);
    JsonResponse jresp = new JsonResponse();
    assertNull(jresp.getResponseObject(response));
  }

}

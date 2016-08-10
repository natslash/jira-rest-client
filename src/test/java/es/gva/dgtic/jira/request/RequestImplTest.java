package es.gva.dgtic.jira.request;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import es.gva.dgtic.jira.request.RequestImpl;

public class RequestImplTest {

  @Test
  public void testRequestImplNullTest() {
    RequestImpl reqImpl = new RequestImpl(null, null);
    assertNotNull("Request is null", reqImpl);
    assertNotNull("Header is null", reqImpl.getHeaders());
    assertNotNull("Request is null", reqImpl.getRequest());
  }

  @Test
  public void testGetHeadersAuthorizationTest() {
    RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
    HttpHeaders headers = reqImpl.getHeaders();
    assertNotNull("Authorization is null", headers.get("Authorization"));
  }

  @Test
  public void testGetHeadersNullCredsTest() {
    RequestImpl reqImpl = new RequestImpl(null, null);
    HttpHeaders headers = reqImpl.getHeaders();
    assertNotNull("Header is null", headers);
  }

  @Test
  public void testGetRequest() {
    RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
    HttpEntity<String> requestString = reqImpl.getRequest();
    assertTrue("Header doens't contain authoirzation key", requestString.getHeaders().containsKey("Authorization"));
  }

  @Test
  public void testGetResponse() {
    boolean exceptionThrown = false;
    RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
    ResponseEntity<String> resp = null;

    try {
      resp = reqImpl.getResponse("http://localhost", HttpMethod.GET, String.class);
      int code = resp.getStatusCode().value();
      System.out.println(code);
    } catch (Exception e) {
      exceptionThrown = true;
    }
    assertTrue("Exception is not thrown", exceptionThrown);
  }

}

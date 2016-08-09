package es.gva.dgtic.jira.request;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of Request interface
 * 
 * @author shashi
 *
 */
public class RequestImpl implements Request {

  private final RestTemplate restTemplate;
  private String base64Creds;
  private HttpHeaders headers;
  private HttpEntity<String> request;

  /**
   * 
   * @param userName
   * @param password
   */
  public RequestImpl(String userName, String password) {
    setBase64Creds(userName, password);
    setHeaders();
    setRequest();
    restTemplate = new RestTemplate();
  }

  /**
   * 
   * @param userName
   * @param password
   */
  private void setBase64Creds(String userName, String password) {

    // Concatenate username & password
    String plainCreds = userName + ":" + password;
    // get Byte[] of plain string
    byte[] plainCredsBytes = plainCreds.getBytes();

    // Encode the byes
    byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);

    // set base64Creds with encoded string
    base64Creds = new String(base64CredsBytes);
  }

  /**
   * 
   * @return headers
   */
  public HttpHeaders getHeaders() {
    return headers;
  }

  /**
   * set headers with basic authorization using encoded credentials
   */
  private void setHeaders() {
    headers = new HttpHeaders();
    // Add the encoded creds header value under the name "Authorization".
    headers.add("Authorization", "Basic " + base64Creds);
  }

  /**
   * 
   * @return
   */
  public HttpEntity<String> getRequest() {
    return request;
  }

  /**
   * Set request with headers
   */
  private void setRequest() {
    this.request = new HttpEntity<String>(headers);
  }

  /**
   * @param url
   * @param method
   * @param responseType
   * 
   */
  @Override
  public <T> ResponseEntity<T> getResponse(String url, HttpMethod method, Class<T> responseType) {
    return restTemplate.exchange(url, method, request, responseType);
  }

}

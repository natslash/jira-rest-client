package es.gva.dgtic.jira.request;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * Interface for HTTPRequests sent
 * 
 * @author shashi
 *
 */
@FunctionalInterface
public interface Request {

  /**
   * This method makes request using Httpmethod, By accessing the url and returns the ResponseEntity. The response entity can hold Json or
   * binary content
   * 
   * @param url
   * @param method
   * @param responseType
   * @return responseEntity
   */
  <T> ResponseEntity<T> getResponse(String url, HttpMethod method, Class<T> responseType);

}

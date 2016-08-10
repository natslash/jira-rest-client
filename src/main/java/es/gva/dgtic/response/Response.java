package es.gva.dgtic.response;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author shashi
 * @param <T>
 *
 */
@FunctionalInterface
public interface Response {

  /**
   * This method takes response entity as parameter and returns JSON object, File
   * @param response
   * @return json, file
   */
  public <T> Object getResponseObject(ResponseEntity<T> response);
}

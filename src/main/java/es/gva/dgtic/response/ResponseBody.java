package es.gva.dgtic.response;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author shashi
 * @param <T>
 *
 */
@FunctionalInterface
public interface ResponseBody {

  /**
   * This method takes response entity as parameter and returns JSON object, File
   * @param response
   * @return json, file
   */
  <T> Object getResponseObject(ResponseEntity<T> response);
}

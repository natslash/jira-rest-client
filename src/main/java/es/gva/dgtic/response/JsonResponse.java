package es.gva.dgtic.response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;

/**
 * JSON implementation of Response interface
 * 
 * @author shashi
 *
 */
public class JsonResponse implements Response {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonResponse.class);

  @Override
  public <T> Object getResponseObject(ResponseEntity<T> response) {
    JSONArray attachment = null;
    
    MediaType mediaType = response.getHeaders().getContentType();
    if (mediaType.equals(MediaType.APPLICATION_JSON_UTF8)) {
      // Convert response body to JSON object
      JSONObject jsonObject = new JSONObject(response.getBody());

      JSONObject data = jsonObject.getJSONObject("fields");
      // Get the field value attachment
      attachment = (JSONArray) data.get("attachment");
      for (int i = 0; i < attachment.length(); i++) {
        // Loop through each attachment
        JSONObject attFile = (JSONObject) attachment.get(i);
        LOGGER.info(attFile.get("content").toString());
        String fileUrl = attFile.get("content").toString();

        // Parse the URL to get the file name
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1, fileUrl.length());
        System.out.println(fileName);
      }
    }
    return attachment;
  }

}

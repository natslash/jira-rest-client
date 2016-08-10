package es.gva.dgtic.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * JSON implementation of Response interface
 * 
 * @author shashi
 *
 */
public class JsonResponseBody implements ResponseBody {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonResponseBody.class);

	private ResponseEntity<String> response;
	private JsonObject jsonObject;

	public JsonResponseBody(ResponseEntity<String> response) {
		setResponse(response);
		setJsonObject();
	}

	private void setResponse(ResponseEntity<String> response) {
		this.response = response;
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject() {
		MediaType mediaType = response.getHeaders().getContentType();

		// Media type is JSON
		if (mediaType.equals(MediaType.APPLICATION_JSON_UTF8)) {
			// Convert response body to JSON object
			String responseBody = response.getBody().toString();
			JsonParser parser = new JsonParser();

			// Create JSON object from the given string
			jsonObject = (JsonObject) parser.parse(responseBody);
		}

		else {
			// The mediatype is not of type JSON
			LOGGER.info("MediaType: " + response.getHeaders().getContentType()
					+ " is not supported with the JSON implementation" + "\n"
					+ "Please use appropriate Response implementation");
		}

	}

	/**
	 * Returns JSON object parsed from responseBody accepts only JSON Media type
	 */
	@Override
	public JsonObject getResponseObject() {
		return jsonObject;
	}

		

}

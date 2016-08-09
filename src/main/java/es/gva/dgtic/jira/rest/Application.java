package es.gva.dgtic.jira.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.gva.dgtic.jira.request.Request;
import es.gva.dgtic.jira.request.RequestImpl;
import es.gva.dgtic.jira.utils.HttpDownloadUtility;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class).close();
	}

	@Override
	public void run(String... args) throws Exception {

		//Set credentials as plain String
		//TODO: parameterize this
		Request request = new RequestImpl("ssubramanyam", "HexaVexa08");
		
		//Set webservice URL
		//TODO: parameterize this
		String webServiceUrl = "https://jira.excentia.es/rest/api/2/issue/OWASP-254";
		
		//Access web service URL and store the response
		ResponseEntity<String> response = request.getResponse(webServiceUrl, HttpMethod.GET, String.class);

		//If the response is OK, proceed
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			//get JSON response as String
			String jsonLine = (String) response.getBody();
			
			//Convert String to JSON object
			JSONObject jsonObject = new JSONObject(jsonLine);
			JSONObject data = jsonObject.getJSONObject("fields");
			//Get the field value attatchment
			JSONArray attachment = (JSONArray) data.get("attachment");
			for (int i = 0; i < attachment.length(); i++) {
				//Loop through each attachment
				JSONObject attFile = (JSONObject) attachment.get(i);
				LOGGER.info(attFile.get("content").toString());
				String fileUrl = attFile.get("content").toString();
				
				//Parse the URL to get the file name
				String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
				ResponseEntity<byte[]> byteResponse = request.getResponse(fileUrl, HttpMethod.GET, byte[].class);
				
				//Call utility method to store the content to a file
				HttpDownloadUtility.downloadFile(fileName, byteResponse);
			}
		}
	}

}
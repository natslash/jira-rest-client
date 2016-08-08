package es.gva.dgtic.jira.request;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestImpl implements Request {

	private RestTemplate restTemplate;
	private String base64Creds;
	private HttpHeaders headers;
	private HttpEntity<String> request;

	public RequestImpl(String userName, String password) {
		setBase64Creds(userName, password);
		setHeaders();
		setRequest();
		restTemplate = new RestTemplate();
	}	

	private void setBase64Creds(String userName, String password) {
		
		//Concatenate username & password
		String plainCreds = userName + ":" + password;
		//get Byte[] of plain string
		byte[] plainCredsBytes = plainCreds.getBytes();
		
		//Encode the byes
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		
		//set base64Creds with encoded string
		base64Creds = new String(base64CredsBytes);
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	private void setHeaders() {
		headers = new HttpHeaders();
		// Add the encoded creds header value under the name "Authorization".
		headers.add("Authorization", "Basic " + base64Creds);
	}

	public HttpEntity<String> getRequest() {
		return request;
	}

	private void setRequest() {
		this.request = new HttpEntity<String>(headers);
	}
	
	@Override
	public <T> ResponseEntity<T> getResponse(String url, HttpMethod method, Class<T> responseType) {
		return restTemplate.exchange(url, method, request, responseType);
	}
	
}

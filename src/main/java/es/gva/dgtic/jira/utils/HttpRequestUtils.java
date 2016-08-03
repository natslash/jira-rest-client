package es.gva.dgtic.jira.utils;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


public class HttpRequestUtils {

	private String plainCreds;
	private String base64Creds;
	private HttpHeaders headers;
	private HttpEntity<String> request;
	
	public HttpRequestUtils(String userName, String password){
		plainCreds = userName + ":" + password;
		setBase64Creds();
		setHeaders(userName, password);
		setRequest();
	}
	
	public HttpHeaders getHeaders() {
		return headers;
	}	
	
	
	public void setHeaders(String userName, String password) {
		
		headers = new HttpHeaders();
		
		//Add the encoded creds header value under the name "Authorization".
        headers.add("Authorization", "Basic " + base64Creds);              
	}
	
	public HttpEntity<String> getRequest() {
		return request;
	}

	public void setRequest() {
		this.request = new HttpEntity<String>(headers);
	}

	public String getBase64Creds() {
		return base64Creds;
	}

	public void setBase64Creds() {
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		base64Creds = new String(base64CredsBytes);
	}
}

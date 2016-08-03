package es.gva.dgtic.jira.utils;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


public class HttpRequestUtils {

	private HttpHeaders headers;
	private HttpEntity<String> request;
	
	public HttpRequestUtils(String userName, String password){
		setHeaders(userName, password);
		setRequest();
	}
	
	public HttpHeaders getHeaders() {
		return headers;
	}	
	
	
	public void setHeaders(String userName, String password) {
		
		//prepare basic authentication String using username and password
		String plainCreds = userName + ":" + userName;
		
		//Encodes this plainCreds into a sequence of bytes 
		byte[] plainCredsBytes = plainCreds.getBytes();
		
		//Encodes all bytes from "plainCredsBytes" byte array into a newly-allocated byte array 
		//using the Base64 encoding.
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		HttpHeaders headers = new HttpHeaders();
		
		//Add the encoded creds header value under the name "Authorization".
        headers.add("Authorization", "Basic " + base64Creds);              
	}
	
	public HttpEntity<String> getRequest() {
		return request;
	}

	public void setRequest() {
		this.request = new HttpEntity<String>(headers);
	}
}

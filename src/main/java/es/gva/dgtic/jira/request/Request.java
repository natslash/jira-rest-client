package es.gva.dgtic.jira.request;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface Request {

	ResponseEntity<String> getResponse(String url, HttpMethod method);
	
	ResponseEntity<byte[]> getResponseAsBytes(String url, HttpMethod method);
}

package es.gva.dgtic.jira.request;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface Request {

	public <T> ResponseEntity<T> getResponse(String url, HttpMethod method, Class<T> responseType);
	
}

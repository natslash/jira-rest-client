package es.gva.dgtic.jira.request.ut;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import es.gva.dgtic.jira.request.RequestImpl;

public class RequestImplTest {

	@Test
	public void testRequestImplNullTest() {
		RequestImpl reqImpl = new RequestImpl(null, null);
		assertNotNull(reqImpl);
		assertNotNull(reqImpl.getHeaders());
		assertNotNull(reqImpl.getRequest());		
	}

	@Test
	public void testGetHeadersAuthorizationTest() {
		RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
		HttpHeaders headers = reqImpl.getHeaders();
		assertNotNull(headers.get("Authorization"));
	}
	
	@Test
	public void testGetHeadersNullCredsTest() {
		RequestImpl reqImpl = new RequestImpl(null, null);
		HttpHeaders headers = reqImpl.getHeaders();
		assertNotNull(headers);		
	}

	@Test
	public void testGetRequest() {
		RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
		HttpEntity<String>requestString = reqImpl.getRequest();
		assertTrue(requestString.getHeaders().containsKey("Authorization"));
	}

	@Test
	public void testGetResponse() {
		RequestImpl reqImpl = new RequestImpl("xxxx", "yyyy");
		ResponseEntity<String> resp = reqImpl.getResponse("http://www.gmail.com", HttpMethod.GET, String.class);
		assertEquals(301,Integer.parseInt(resp.getStatusCode().toString()));
	}

}

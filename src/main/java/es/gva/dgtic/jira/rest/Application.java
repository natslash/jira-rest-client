package es.gva.dgtic.jira.rest;

import org.springframework.boot.CommandLineRunner;

import java.nio.charset.Charset;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	String plainCreds = "ssubramanyam:HexaVexa08";
	byte[] plainCredsBytes = plainCreds.getBytes();
	byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Override
    public void run(String... args) throws Exception {
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response;
        response = restTemplate.exchange("https://jira.excentia.es/rest/api/2/issue/OWASP-254?fields=attachment", HttpMethod.GET, request,Object.class);
        
        if(response.getStatusCode().equals(HttpStatus.OK)){
        	ObjectMapper mapper = new ObjectMapper();
        	log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        }
    }

}
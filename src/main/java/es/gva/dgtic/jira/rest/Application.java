package es.gva.dgtic.jira.rest;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import es.gva.dgtic.jira.issue.Attachment;
import es.gva.dgtic.jira.issue.Example;
import es.gva.dgtic.jira.issue.Fields;
import es.gva.dgtic.jira.request.Request;
import es.gva.dgtic.jira.utils.FileSaveUtility;
import es.gva.dgtic.response.JsonResponseBody;
import es.gva.dgtic.response.ResponseBody;

/**
 * Application entry point
 * 
 * @author shashi
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  /**
   * 
   * @param args
   */
  public static void main(String args[]) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args).close();
  }

  /**
   * @param args
   */
  @Override
  public void run(String... args) throws Exception {

    // Set credentials as plain String
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    Request request = (Request) context.getBean("request");
    context.close();

    // Read from command line argument and set webservice URL
    String webServiceUrl = args[0];

    // Access web service URL and store the response
    ResponseEntity<String> response = request.getResponse(webServiceUrl, HttpMethod.GET, String.class);

    // If the response is OK, proceed
    if (response.getStatusCode().equals(HttpStatus.OK)) {   
     
      //Get ResponseBody
      ResponseBody jsonResponse = new JsonResponseBody(response);
      
      //Get Json object "fields"
      JsonObject jsonBody = (JsonObject) jsonResponse.getResponseBody();
      
      Gson gson = new Gson();      
      //Convert JSON to Fields POJO
      Example issue = gson.fromJson(jsonBody, Example.class);
      
      //Get fields
      Fields fields = issue.getFields();
      
      // Get the attachments list
      List<Attachment> attachments = fields.getAttachment();      
      
      
      Iterator<Attachment> iterator = attachments.iterator();
      while(iterator.hasNext()) {
        // Loop through each attachment
        Attachment attachment = iterator.next();
        
        // Get file name
        String fileName = attachment.getFilename();
        LOGGER.info(attachment.getFilename());
        
        //Get Url to dwonload the file
        String fileUrl = attachment.getContent();        
        LOGGER.info(fileUrl);
        
        //Download file 
        ResponseEntity<byte[]> byteResponse = request.getResponse(fileUrl, HttpMethod.GET, byte[].class);

        // Call utility method to save file
        FileSaveUtility.saveFile(fileName, byteResponse);
      }
    }
  }

}
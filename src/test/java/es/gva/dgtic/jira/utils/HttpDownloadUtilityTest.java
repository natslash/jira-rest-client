package es.gva.dgtic.jira.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { HttpStatus.class })
public class HttpDownloadUtilityTest {

  @Test  
  public void testDownloadFileStatusNotOK() throws IOException {
    String fileName = "filename.jar";
    
    
    @SuppressWarnings("unchecked")
    ResponseEntity<byte[]> byteResponse = mock(ResponseEntity.class);
    HttpStatus status = mock(HttpStatus.class);
    
    when(byteResponse.getStatusCode()).thenReturn(status);    
    HttpDownloadUtility.downloadFile(fileName, byteResponse);
  }
  
  @Test  
  public void testDownloadFileStatusOK() throws IOException {
    String fileName = "filename.jar";
    
    
    @SuppressWarnings("unchecked")
    ResponseEntity<byte[]> byteResponse = mock(ResponseEntity.class);
    HttpStatus status = mock(HttpStatus.class);
    
    when(byteResponse.getStatusCode()).thenReturn(status); 
    when(status.value()).thenReturn(200);
    HttpDownloadUtility.downloadFile(fileName, byteResponse);
  }

}

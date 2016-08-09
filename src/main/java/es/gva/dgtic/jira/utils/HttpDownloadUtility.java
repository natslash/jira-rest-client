package es.gva.dgtic.jira.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author @author shashi
 *
 */
public class HttpDownloadUtility {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpDownloadUtility.class);

  /**
   * Downloads a file from a URL
   * 
   * @param fileName
   *          Name of the file to be downloaded
   * @param byteResponse
   *          byte Response obtained
   * @throws IOException
   */
  public static void downloadFile(String fileName, ResponseEntity<byte[]> byteResponse) throws IOException {

    int responseCode = Integer.parseInt(byteResponse.getStatusCode().toString());

    // Check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
      LOGGER.info("fileName = " + fileName);

      // Create new directory called "a_desplegar"
      File dir = new File("a_desplegar");
      try {
        // Create the directory (might throw permissions exception)
        dir.mkdir();
      } catch (SecurityException se) {
        LOGGER.info(se.getLocalizedMessage());
        throw se;
      }
      // Get the file name from the URL
      String saveFilePath = "a_desplegar" + File.separator + fileName;

      // Open output stream
      FileOutputStream output = new FileOutputStream(new File(saveFilePath));

      // Write the contents to the file
      IOUtils.write(byteResponse.getBody(), output);

      LOGGER.info("File downloaded");
    } else {
      LOGGER.info("No file to download. Server replied with HTTP code: " + responseCode);
    }
  }
}
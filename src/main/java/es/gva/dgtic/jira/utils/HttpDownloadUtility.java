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
	
	private static final Logger log = LoggerFactory.getLogger(HttpDownloadUtility.class);
	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL
	 *            HTTP URL of the file to be downloaded
	 * @param request
	 *            HttpEntity which contains authentication information
	 * @throws IOException
	 */
	public static void downloadFile(String fileName, ResponseEntity<byte[]> byteResponse) throws IOException {
		
		int responseCode = byteResponse.getStatusCodeValue();

		// Check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("fileName = " + fileName);
			
			File dir = new File("a_desplegar");
			dir.mkdir();
			String saveFilePath = "a_desplegar"+ File.separator + fileName;
			FileOutputStream output = new FileOutputStream(new File(saveFilePath));

			IOUtils.write(byteResponse.getBody(), output);

			log.info("File downloaded");
		} else {
			log.info("No file to download. Server replied with HTTP code: " + responseCode);
		}
	}
}
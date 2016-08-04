package es.gva.dgtic.jira.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import es.gva.dgtic.jira.rest.Application;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author www.codejava.net
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

			FileOutputStream output = new FileOutputStream(new File(fileName));

			IOUtils.write(byteResponse.getBody(), output);

			log.info("File downloaded");
		} else {
			log.info("No file to download. Server replied with HTTP code: " + responseCode);
		}
	}
}
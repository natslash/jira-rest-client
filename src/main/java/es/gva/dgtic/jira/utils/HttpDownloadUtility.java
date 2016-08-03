package es.gva.dgtic.jira.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author www.codejava.net
 *
 */
public class HttpDownloadUtility {
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

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("fileName = " + fileName);

			FileOutputStream output = new FileOutputStream(new File(fileName));

			IOUtils.write(byteResponse.getBody(), output);

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
	}
}
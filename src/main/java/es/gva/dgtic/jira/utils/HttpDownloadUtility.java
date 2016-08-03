package es.gva.dgtic.jira.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author www.codejava.net
 *
 */
public class HttpDownloadUtility {
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL
	 *            HTTP URL of the file to be downloaded
	 * @param saveDir
	 *            path of the directory to save the file
	 * @throws IOException
	 */
	public static void downloadFile(String fileURL, HttpEntity<String> request) throws IOException {
		URL url = new URL(fileURL);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> fileResp = restTemplate.exchange(fileURL, HttpMethod.GET, request, byte[].class);

		int responseCode = fileResp.getStatusCodeValue();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";

			// extracts file name from URL
			fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());

			System.out.println("fileName = " + fileName);

			FileOutputStream output = new FileOutputStream(new File(fileName));

			IOUtils.write(fileResp.getBody(), output);

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
	}
}
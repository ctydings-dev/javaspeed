/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Product;
import org.json.*;

/**
 *
 * @author ctydi
 */
public abstract class LightspeedCaller {

    private final String url;
    private final String token;

    public LightspeedCaller(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String getURL() {
        return this.url;
    }

    public String getToken() {
        return this.token;
    }

    protected String create20API(String path) {
        return "api/2.0/" + path;
    }

    protected String create09API(String path) {
        return "api/" + path;
    }

    protected String createGetURL(String path, int page, int pageSize) {

        String ret = this.getURL() + "/" + path;
        if (page > 0 || pageSize > 0) {
            if (page > 0) {
                ret = ret + "?page=" + page;
                if (pageSize > 0) {
                    ret = ret + "&page_size=" + pageSize;
                }
            } else {
                ret = ret + "?page_size=" + pageSize;
            }
        }
        return ret;
    }

    protected String sendGetRequest(String path, int pageSize) throws MalformedURLException, IOException {
        final String charset = "UTF-8";
        String urlToRead;
        // Create the connection

        String url = this.createGetURL(path, 0, pageSize);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + this.getToken());
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null) {
            inputStream = connection.getInputStream();
        }

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuffer response = new StringBuffer();

        String out = "";
        while ((inputLine = responseReader.readLine()) != null) {
            out = out + inputLine + "\n";
            response.append(inputLine);

        }
        responseReader.close();
        return out;

    }

    protected void sendPostRequest(String path, String JSON) throws MalformedURLException, IOException {
        final String charset = "UTF-8";
        String urlToRead;
        // Create the connection
        HttpURLConnection connection = (HttpURLConnection) new URL(this.getURL() + "/" + path).openConnection();
        // setDoOutput(true) implicitly set's the request type to POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + this.getToken());

        try ( OutputStream os = connection.getOutputStream()) {
            byte[] input = JSON.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Check the error stream first, if this is null then there have been no issues with the request
        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null) {
            inputStream = connection.getInputStream();
        }

        // Read everything from our stream
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuffer response = new StringBuffer();
        List<String> ret = new ArrayList<String>();
        while ((inputLine = responseReader.readLine()) != null) {

            response.append(inputLine);

        }
        responseReader.close();

    }

}

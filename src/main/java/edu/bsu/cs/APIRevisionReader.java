package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIRevisionReader {

    public String retrieveRevisionsFromAPI(String articleTitle) throws IOException {
        URL url = createURL(articleTitle);
        try {
            HttpURLConnection stableConnection = (HttpURLConnection) url.openConnection();
            stableConnection.setRequestMethod("GET");
            stableConnection.setRequestProperty("User-Agent", "Revision Reporter/0.1 jason/yoder2@bsu.edu");
            InputStream dataStream = stableConnection.getInputStream();
            return new String(dataStream.readAllBytes());
        } catch(IOException e) {
            throw new IOException("Error: Connection Failure", e);
        }
    }

    public URL createURL(String articleSearch) throws IOException{
        String encodedSearch = URLEncoder.encode(articleSearch, StandardCharsets.UTF_8);
        String apiSearchUrl = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                encodedSearch + "&redirects=1&formatversion=2&rvprop=timestamp%7Cuser&rvlimit=15";

        return URI.create(apiSearchUrl).toURL();
    }
}
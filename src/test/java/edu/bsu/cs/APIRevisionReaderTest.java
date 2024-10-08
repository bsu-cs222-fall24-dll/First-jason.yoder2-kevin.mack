package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;


public class APIRevisionReaderTest {


    @Test
    public void testCreateURL() throws IOException {
        APIRevisionReader reader = new APIRevisionReader();
        String result = String.valueOf(reader.createURL("Zappa"));
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa" +
                "&redirects=1&formatversion=2&rvprop=timestamp%7Cuser&rvlimit=15", result);
    }


    @Test
    public void testRetrieveRevisionsFromAPI() throws IOException {
        APIRevisionReader reader = new APIRevisionReader();
        boolean testFail = true;
        String search = "Zappa";
        String data = reader.retrieveRevisionsFromAPI(search);
        if (!data.isEmpty()) {
            testFail = false;
        }
        Assertions.assertFalse(testFail);
    }

    //This test will only work if you disable internet connection on your PC
    //Suppressed warning below because of internet connection being needed for the majority of tests
    @Test
    public void testConnectionFailure(){
        APIRevisionReader reader = new APIRevisionReader();
        String search = "Zappa";
        Assertions.assertThrows(IOException.class, () -> reader.retrieveRevisionsFromAPI(search));
    }
}
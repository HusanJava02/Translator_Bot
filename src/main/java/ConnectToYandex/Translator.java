package ConnectToYandex;


import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Translator {
    public  String translate(String langFrom,String langTo,String text) throws IOException, URISyntaxException {

        String urlStr = "https://script.google.com/macros/s/AKfycbwyIonA9J8yO0fFb-1zgQ6KHL5cYxa1iIKMrZakvuII9qh3WUiTPw8WrYBA3zhKOTPJ7A/exec";

        return translateGoogle(urlStr,langFrom,langTo,text);
    }
    private  String translateGoogle(String urls,String langFrom, String langTo, String text) throws IOException, URISyntaxException {
        // INSERT YOU URL HERE
        String urlStr = urls +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        System.out.println(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String line = response.toString().replace("&#39;","'");
        return line;
    }
}

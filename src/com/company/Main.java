package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    private static String baseUrl = "https://jsonmock.hackerrank.com/api/inventory?barcode=";
    private static JSONParser parser = new JSONParser();

    public static void main(String[] args) throws IOException, ParseException {
        getPrice(74001755);
    }

    public static int getPrice(int barCode) throws IOException, ParseException {
        URL url = new URL(baseUrl + barCode);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder responseDataBuilder = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) responseDataBuilder.append(line);
        JSONObject jsonResponse = (JSONObject) parser.parse(responseDataBuilder.toString());
        JSONArray jsonArray = (JSONArray) jsonResponse.get("data");
        JSONObject data = (JSONObject) jsonArray.get(0);
        System.out.println(data.get("price"));
        System.out.println(data.get("discount"));
        return 0;
    }
}

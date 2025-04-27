package com.jobfit.server.support.recurit;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RecruitSupport {
  String API_KEY = "64595357567470703935734c417748";
  String API_URL = "http://openapi.seoul.go.kr:8088/";

  public JSONObject getJobInfo(int start, int end) throws Exception {
    String apiUrl = API_URL + API_KEY + "/json/GetJobInfo/" + String.valueOf(start) + "/" + String.valueOf(end);
    URL url = new URL(apiUrl);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = br.readLine()) != null) {
      content.append(inputLine);
    }
    br.close();
    con.disconnect();
    JSONObject json = new JSONObject(content.toString());
    return json;
  }
}
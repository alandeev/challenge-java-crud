package com.alan.crud.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class Request {
  private String request_url; 

  public Request(String requestUrl) {
    this.request_url = requestUrl;
  }
  
  public CompletableFuture<JSONObject> execute() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.request_url)).build();
    return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(Request::parse);
  }

  public static JSONObject parse(String stringToParse) {
    JSONObject json = new JSONObject();

    try{
      JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
      json = (JSONObject) parser.parse(stringToParse);
    }catch(Exception e) {
      e.printStackTrace();
    }

    return json;
  }
}

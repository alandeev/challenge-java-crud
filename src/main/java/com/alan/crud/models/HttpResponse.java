package com.alan.crud.models;
import org.springframework.http.ResponseEntity;
import net.minidev.json.JSONObject;

public class HttpResponse {
  String message = "";
  Integer statusCode = 200;
  Object data;

  public HttpResponse(String message, Integer statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Integer getStatusCode() {
    return this.statusCode;
  }

  public ResponseEntity<JSONObject> extract() {
    JSONObject response = new JSONObject();

    if(this.statusCode >= 200 && this.statusCode <= 299) {
      response.put("status", "success");
    } else {
      response.put("status", "error");
    }

    response.put("message", this.message);
    response.put("data", this.data);

    return ResponseEntity
      .status(this.statusCode)
      .body(response);
  }
}

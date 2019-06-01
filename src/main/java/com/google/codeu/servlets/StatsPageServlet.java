package com.google.codeu.servlets;

import java.io.IOException;
import java.util.*; 

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.codeu.data.Datastore;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.Gson;

/**
 * Handles fetching site statistics.
 */
@WebServlet("/stats")
public class StatsPageServlet extends HttpServlet{

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  /**
   * Responds with site statistics in JSON.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("application/json");

    int messageCount = datastore.getTotalMessageCount();
	int userCount = datastore.getTotalUserCount();
	int averageMessageLength = datastore.getAverageMessageLength();
	// List<String> user= datastore.getMostActiveUser();
	String mostActiveUser = datastore.getMostActiveUser();

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("messageCount", messageCount);
    jsonObject.addProperty("userCount", userCount);
    jsonObject.addProperty("averageMessageLength", averageMessageLength);
    jsonObject.addProperty("mostActiveUser", mostActiveUser);
	
	// String mostActiveUser = new Gson().toJson(user);
    // jsonObject.addProperty("mostActiveUser", mostActiveUser);

	
    response.getOutputStream().println(jsonObject.toString());
  }
}
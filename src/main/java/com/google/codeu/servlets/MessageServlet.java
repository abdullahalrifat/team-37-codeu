/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

//import org.primeframework.transformer.service.BBCodeParser;

/** Handles fetching and saving {@link Message} instances. */
@WebServlet("/messages")
public class MessageServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
	  datastore = new Datastore();
  }

  /**
   * Responds with a JSON representation of {@link Message} data for a specific user. Responds with
   * an empty array if the user is not provided.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    String user = request.getParameter("user");

    if (user == null || user.equals("")) {
		response.getWriter().println("[]");
		return;
    }

    List<Message> messages = datastore.getMessages(user);
    Gson gson = new Gson();
    String json = gson.toJson(messages);

    response.getWriter().println(json);
  }

  /** Stores a new {@link Message}. */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
		response.sendRedirect("/index.html");
		return;
    }

	String user = userService.getCurrentUser().getEmail();
	String text = Jsoup.clean(request.getParameter("text"), Whitelist.none());

  TextProcessor processor = BBProcessorFactory.getInstance().create();
  String input= processor.process( text ) ;
	String city = Jsoup.clean(request.getParameter("autocomplete_search"), Whitelist.none());
	double alat = Double.parseDouble(request.getParameter("alat"));
	double along = Double.parseDouble(request.getParameter("along"));

      Message message = null;
      try {
          message = new Message(user,input,city,alat,along);
      } catch (ParseException e) {
          e.printStackTrace();
      }
      datastore.storeMessage(message);
    response.sendRedirect("/user-page.html?user=" + user);
  }

  public String replaceUserText(String userText) {
	String regex =  new String();
	String replacement = new String();

	// youtube videos. validity is checked in the regex
	if (userText.contains("youtube")) {
		regex = "(http)(s?:\\/\\/www\\.youtube\\.com\\/watch\\?v=)([a-zA-Z0-9]+)";
		replacement = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/$3\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
	}

	// images and gifs
	else {
		regex = "(\\!\\[([A-Za-z0-9]+[.?!,\"\'A-Za-z0-9\\s]*)\\])?(https?://[a-zA-Z0-9]+\\S*\\.(png|jpg|gif|jpeg|svg))";
		replacement = "<figure><img src=\"$3\"><figcaption>$2</figcaption></figure>";
	}
	return userText.replaceAll(regex, replacement);
  }
	
  // public boolean urlValidator (String url) {
	 // UrlValidator urlValidator = new UrlValidator();
	 // return urlValidator.isValid(url);
  // }
}

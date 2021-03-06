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
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

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

    String keyword = request.getParameter("keyword");

    if (keyword == null || keyword.equals("")) {
      // Request is invalid, return empty array
      response.getWriter().println("[]");
      return;
    }
	
	List<Message> messages = datastore.search(keyword);
	
	if (messages != null) {
		for (Message text: messages) {
			System.out.println("message found!");
		}
	}
	else 
		System.out.println("message not found!");
			
	Gson gson = new Gson();
	String json = gson.toJson(messages);
	response.getWriter().println(json);

}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
		  response.sendRedirect("/index.html");
		  return;
		}
		
		response.setContentType("application/json");
		String keyword = request.getParameter("keyword");
		response.sendRedirect("/results.html?keyword=" + keyword);
	}
}
package com.google.codeu.servlets;

import java.io.IOException;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;

/**
 * Handles fetching and saving user data.
 */
@WebServlet("/about")
public class AboutMeServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  /**
   * Responds with the "about me" section for a particular user.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("text/html");

    String user = request.getParameter("user");

    if(user == null || user.equals("")) {
      // Request is invalid, return empty response
      return;
    }

    User userData = datastore.getUser(user);

    if(userData == null || userData.getAboutMe() == null) {
      return;
    }

    response.getOutputStream().println(userData.getAboutMe());
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/index.html");
      return;
    }

    String userEmail = userService.getCurrentUser().getEmail();
    String aboutMe = Jsoup.clean(request.getParameter("about-me"), Whitelist.none());
		
	String textReplaced = replaceUserText(aboutMe);

    User user = new User(userEmail, textReplaced);
    datastore.storeUser(user);

    response.sendRedirect("/user-page.html?user=" + userEmail);
  }
    public String replaceUserText(String aboutMe) {
	
	String regex =  new String();
	String replacement = new String();
	
	// youtube videos. validity is checked in the regex
	
	if (aboutMe.contains("youtube")) {
		regex = "(http)(s?:\\/\\/www\\.youtube\\.com\\/watch\\?v=)([a-zA-Z0-9]+)";
		replacement = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/$3\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";		
	}
	
	// images and gifs
	
	else {
		regex = "(\\!\\[([A-Za-z0-9]+[.?!,\"\'A-Za-z0-9\\s]*)\\])?(https?://[a-zA-Z0-9]+\\S*\\.(png|jpg|gif|jpeg|svg))";
		replacement = "<figure><img src=\"$3\"><figcaption>$2</figcaption></figure>";
	}		
		
	return aboutMe.replaceAll(regex, replacement);
 
 }
  
}
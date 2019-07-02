package com.google.codeu.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Scanner;

@WebServlet("/placechart")
public class ChartServlet extends HttpServlet {

  private JsonArray placeRatingArray;
	// This class could be its own file if we needed it outside this servlet
	private static class placeRating {
    String title;
    double rating;
    private placeRating (String title, double rating) {
      this.title = title;
      this.rating = rating;
    }
  }
  @Override
  public void init() {
    placeRatingArray = new JsonArray();
    Gson gson = new Gson();
    Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/place-ratings.csv"));
    scanner.nextLine(); //skips first line (the csv header)
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] cells = line.split(",");
      String curTitle = cells[2];
      double curRating = Double.parseDouble(cells[3]);
      placeRatingArray.add(gson.toJsonTree(new placeRating(curTitle, curRating)));
    }
    scanner.close();
  }
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     response.setContentType("application/json");
     response.getOutputStream().println(placeRatingArray.toString());
   }
}

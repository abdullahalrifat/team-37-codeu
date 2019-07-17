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
import com.google.codeu.data.Mates;
import com.google.codeu.data.Guides;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


/** Handles fetching and saving {@link Message} instances. */
@WebServlet("/guides")
public class GuideServlet extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // response.setContentType("application/json");
        response.setContentType("text/html");

        List<Guides> guides = datastore.getAllGuide();
        // System.out.println("get method");
        // System.out.println(guides.get(0).getTimestamp());

        // Gson gson = new Gson();
        // String json = gson.toJson(guides);

        // request.setAttribute("guides", json);
        request.setAttribute("guides", guides);
        request.setAttribute("guidesCount", guides.size());

        request.getRequestDispatcher("/guide-list.jsp").forward(request,response);

        // RequestDispatcher rd = request.getRequestDispatcher("/guide-list.jsp");
        // rd.forward(request, response);

        // response.getWriter().println(json);
        // request.setAttribute('guides', guides);
        // RequestDispatcher rd = request.getRequestDispatcher("guide-list.jsp");
        // rd.forward(request, response);
        /*try {
            // code that throws an Exception
            rd.forward(request, response);
            // rd.include(request, response);
        } catch (Exception e) {
            // throw new ServletException(e);
            System.println.out('hello world');
        }*/
        // request.getRequestDispatcher("/guide-list.jsp").forward(request, response);
        // System.out.println('hello world');
    }

    /** Stores a new {@link Message}. */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
        String address = Jsoup.clean(request.getParameter("address"), Whitelist.none());
        double contact_no = Double.parseDouble(request.getParameter("contact_no"));
        String gender = Jsoup.clean(request.getParameter("gender"), Whitelist.none());
        String location = Jsoup.clean(request.getParameter("location"), Whitelist.none());
        double charge = Double.parseDouble(request.getParameter("charge"));
        double alat = Double.parseDouble(request.getParameter("alat"));
        double along = Double.parseDouble(request.getParameter("along"));

        System.out.println(request.getParameter("alat"));
        System.out.println(request.getParameter("along"));

        Guides guide = new Guides(name, address, contact_no, gender, location, charge, alat, along);
        datastore.storeGuide(guide);

        response.sendRedirect("/guides");
    }



}
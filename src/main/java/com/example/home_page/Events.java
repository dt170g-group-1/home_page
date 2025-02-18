/*
Created by IntelliJ IDEA.
User: Johannes
Date: 2025-02-12
Time: 11:15
To change this template use File | Settings | File Templates.
 */

package com.example.home_page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "events", value = "/events-coming")
public class Events extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        List<AddEvents> events = new ArrayList<>();
        events.add(new AddEvents("Antons 20-årsjubileum", "2025-03-01", "20:00"));
        events.add(new AddEvents("Midsommarfirande", "2025-06-20", "17:00"));

        /*
        request.setAttribute("next_event", events);

        RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
        dispatcher.forward(request, response);

         */

        if (!events.isEmpty()) {
            out.println("<div id='events'>");
            out.println("<h2>Events</h2>");
            AddEvents event = events.get(0);
            out.println("<b>" + event.getEvent() + "</b>");
            out.println("<p>Date: " + event.getDate() + "</p>");
            out.println("<p>Time: " + event.getTime() + "</p>");
        }
    }
}

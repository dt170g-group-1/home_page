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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "events", value = "/events-coming")
public class Events extends HttpServlet {

    private List<AddEvents> getEventsDataFromExcel() throws IOException {
        List<AddEvents> eventsList = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/events.xlsx")) {
            assert is != null;
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header
                }
                String event = row.getCell(0).getStringCellValue();
                String date = getCellValueAsString(row.getCell(1));
                String time = getTimeValueAsString(row.getCell(2));
                eventsList.add(new AddEvents(event, date, time));
            }
        }
        return eventsList;
    }

    private String getCellValueAsString(Cell cell) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cell.getDateCellValue());
    }

    private String getTimeValueAsString(Cell cell) {
        String time = cell.getDateCellValue().toString();
        return time.substring(11, 16);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        List<AddEvents> events = getEventsDataFromExcel();

        /*
        request.setAttribute("next_event", events);

        RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
        dispatcher.forward(request, response);
        */


        if (!events.isEmpty()) {
            // Sort events by date
            events.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));

            // Find the next upcoming event date
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            String nextEventDate = null;
            for (AddEvents event : events) {
                if (event.getDate().compareTo(today) >= 0) {
                    nextEventDate = event.getDate();
                    break;
                }
            }

            if (nextEventDate != null) {
                out.println("<div id='events'>");
                out.println("<h3>Upcoming Events</h3>");
                for (AddEvents event : events) {
                    if (event.getDate().equals(nextEventDate)) {
                        out.println("<b>" + event.getEvent() + "</b>");
                        out.println("<p>Date: " + event.getDate() + "</p>");
                        out.println("<p>Time: " + event.getTime() + "</p>");
                    }
                }
                out.println("</div>");
            } else {
                out.println("<div id='events'>");
                out.println("<h2>No upcoming events</h2>");
                out.println("</div>");
            }
        }
    }
}

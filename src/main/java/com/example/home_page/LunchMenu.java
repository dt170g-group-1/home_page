/*
Created by IntelliJ IDEA.
User: Johannes
Date: 2025-02-12
Time: 11:14
To change this template use File | Settings | File Templates.
 */
package com.example.home_page;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "lunchMenu", value = "/lunch-menu")
public class LunchMenu extends HttpServlet {

    private DayOfWeek getDayOfWeek(String day) { // added because of a error java doesn't like (ÅÄÖ)
        switch (day.toLowerCase()) {
            case "måndag": return DayOfWeek.MONDAY;
            case "tisdag": return DayOfWeek.TUESDAY;
            case "onsdag": return DayOfWeek.WEDNESDAY;
            case "torsdag": return DayOfWeek.THURSDAY;
            case "fredag": return DayOfWeek.FRIDAY;
            case "lördag": return DayOfWeek.SATURDAY;
            case "söndag": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        List<AddLunch> lunchMenu = new ArrayList<>();
        lunchMenu.add(new AddLunch("Måndag", "Wallenbergare", "med hemslagen brunsås Rårörda Lingon, Potatispuré, Gröna Ärtor", 100));
        lunchMenu.add(new AddLunch("Tisdag", "Steak minute", "med en hemslagen bearnaise, kryddig pommes & krispig spenatsallad med parmesan, picklad silverlök och rostade hasselnötter.", 120));
        lunchMenu.add(new AddLunch("Onsdag", "Grillad kycklingfilé", "med en krämig svampsås, rostad potatis och en sallad på färsk spenat, rödlök, tomat och fetaost.", 110));
        lunchMenu.add(new AddLunch("Torsdag", "Antons Kötfärs", "med en hemslagen brunsås, Rårörda Lingon, Potatismos och Pressgruka", 100));
        lunchMenu.add(new AddLunch("Fredag", "Grillad laxfilé", "med en krämig hummersås, kokt potatis och en sallad på färsk spenat, rödlök, tomat och fetaost.", 130));


        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String todayString = "";
        switch (today) {
            case MONDAY: todayString = "Måndag"; break;
            case TUESDAY: todayString = "Tisdag"; break;
            case WEDNESDAY: todayString = "Onsdag"; break;
            case THURSDAY: todayString = "Torsdag"; break;
            case FRIDAY: todayString = "Fredag"; break;
            // Add cases for Saturday and Sunday if needed
        }

        out.println("<div id='lunch'>");
        out.println("<h2>Dagens Lunch   11:00 - 14:00 </h2>");
        for (AddLunch item : lunchMenu) {
            if (item.getDay().equals(todayString)) {
                out.println("<b>" + item.getDay() + "</b>");
                out.println("<p><b>" + item.getDish() + "</b> " + item.getDescription() + "</p>" + "<br><br>");

            }
        }

        out.println("<div id='lunch'>");
        out.println("<h3>Veckans Lunch  11:00 - 14:00</h3>");
        for (AddLunch item : lunchMenu) {
            DayOfWeek lunchDay =  getDayOfWeek(item.getDay());
            if (lunchDay.compareTo(today) >= 0) {
                out.println("<b>" + item.getDay() + "</b>");
                out.println("<p><b>" + item.getDish() + "</b> " + item.getDescription() + "</p>");
            }
        }
        out.println("</div>");
    }
    public void destroy() {
    }
}

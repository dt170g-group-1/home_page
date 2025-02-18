package com.example.home_page;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "lunchMenu", value = "/lunch-menu")
public class LunchMenu extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        List<AddLunch> lunchMenu = new ArrayList<>();
        lunchMenu.add(new AddLunch("Måndag", "Wallenbergare", "med hemslagen brunsås Rårörda Lingon, Potatispuré, Gröna Ärtor", 100));
        lunchMenu.add(new AddLunch("Tisdag", "Steak minute", "med en hemslagen bearnaise, kryddig pommes & krispig spenatsallad med parmesan, picklad silverlök och rostade hasselnötter.", 120));
        lunchMenu.add(new AddLunch("Onsdag", "Grillad kycklingfilé", "med en krämig svampsås, rostad potatis och en sallad på färsk spenat, rödlök, tomat och fetaost.", 110));
        lunchMenu.add(new AddLunch("Torsdag", "Antons Kötfärs", "med en hemslagen brunsås, Rårörda Lingon, Potatismos och Pressgruka", 100));
        lunchMenu.add(new AddLunch("Fredag", "Grillad laxfilé", "med en krämig hummersås, kokt potatis och en sallad på färsk spenat, rödlök, tomat och fetaost.", 130));

        out.println("<div id='lunch'>");
        out.println("<h3>Veckans Lunch - 11:00 - 14:00</h3>");
        for (AddLunch item : lunchMenu) {
            out.println("<b>" + item.getDay() + "</b>");
            out.println("<p><b>" + item.getDish() + "</b> " + item.getDescription() + "</p>");
        }
        out.println("</div>");

    }
    public void destroy() {
    }
}

/*
Created by IntelliJ IDEA.
User: Johannes / Hannes
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.menu;

@WebServlet(name = "lunchMenu", value = "/lunch-menu")
public class LunchMenu extends HttpServlet {
    private List<AddLunch> getLunchDataFromExcel() throws IOException {
        List<AddLunch> lunchList = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/menu.xlsx")) {
            assert is != null;
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header
                }
                String day = row.getCell(0).getStringCellValue();
                String dish = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();
                double price = row.getCell(3).getNumericCellValue();
                lunchList.add(new AddLunch(day, dish, description, (int)price));
            }
        }
        return lunchList;
    }


    private DayOfWeek getDayOfWeek(String day) { // added because of an error java doesn't like (ÅÄÖ)
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
        List<AddLunch> lunchMenu = getLunchDataFromExcel();


        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String todayString = switch (today) {
            case MONDAY -> "Måndag";
            case TUESDAY -> "Tisdag";
            case WEDNESDAY -> "Onsdag";
            case THURSDAY -> "Torsdag";
            case FRIDAY -> "Fredag";
            default -> "";
            // Add cases for Saturday and Sunday if needed
        };

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

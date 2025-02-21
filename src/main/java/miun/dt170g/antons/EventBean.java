package miun.dt170g.antons;


import com.example.home_page.AddEvents;
import com.example.home_page.Events;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class EventBean {
    private List<Event> getEventsDataFromExcel() throws IOException {
        List<Event> eventsList = new ArrayList<>();
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
                eventsList.add(new Event(event, date, time));
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

    public List<Event> getComingEvent() throws IOException {
        List<Event> allEvent = getEventsDataFromExcel();
        List<Event> nextComingEvent = new ArrayList<>();

        if(!allEvent.isEmpty()){
            allEvent.sort((e1,e2) -> e1.getDate().compareTo(e2.getDate()));

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            String nextEventDate = null;
            for (Event event : allEvent) {
                if (event.getDate().compareTo(today) >= 0) {
                    nextEventDate = event.getDate();
                    break;
                }
            }
            for (Event event : allEvent){
                if(event.getDate().equals(nextEventDate)){
                    nextComingEvent.add(event);
                }
            }
        }
        return nextComingEvent;
    }
}

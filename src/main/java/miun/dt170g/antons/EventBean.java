package miun.dt170g.antons;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Base64;


@Named
@RequestScoped
public class EventBean {
    private List<Event> getEventsDataFromExcel() throws IOException {
        List<Event> eventsList = new ArrayList<>();
        File file = new File("events.xlsx");
        try (InputStream is = new FileInputStream(file)) {
            assert is != null;
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header
                }
                String event = row.getCell(0).getStringCellValue();
                String date = getCellValueAsString(row.getCell(1));
                String time = getTimeValueAsString(row.getCell(2));
                byte[] image = extractImageFromWorkbook(workbook);
                String imageUrl = Base64.getEncoder().encodeToString(image);
                eventsList.add(new Event(event, date, time, image, imageUrl));
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

    private byte[] extractImageFromWorkbook(Workbook workbook) {
        List<XSSFPictureData> pictures = ((XSSFWorkbook) workbook).getAllPictures();
        if (pictures.isEmpty()) {
            return null; // No images found
        }
        XSSFPictureData pictureData = pictures.get(0);
        return pictureData.getData();
    }


    public List<Event> getComingEvent() throws IOException {
        List<Event> allEvent = getEventsDataFromExcel();
        List<Event> nextComingEvent = new ArrayList<>();

        if(!allEvent.isEmpty()){
            allEvent.sort((e1,e2) -> e1.getDate().compareTo(e2.getDate()));

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            for (Event event : allEvent) {
                if (event.getDate().compareTo(today) >= 0) {
                    nextComingEvent.add(event);
                    break;
                }
            }
        }
        return nextComingEvent;
    }
}

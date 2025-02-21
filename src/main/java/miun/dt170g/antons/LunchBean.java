package miun.dt170g.antons;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class LunchBean {

    public List<Lunch> getLunches() throws IOException {
        return getLunchesFromExcel();
    }

    public List<Lunch> getTodaysLunch() throws IOException {
        List<Lunch> thisWeeksLunches = getLunchesFromExcel();
        List<Lunch> todaysLunch = new ArrayList<>();
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String todaysString = getDayString(today);

        for (Lunch lunch : thisWeeksLunches){
            if(lunch.getDay().equals(todaysString)){
                todaysLunch.add(lunch);
            }
        }
        return todaysLunch;
    }

    public List<Lunch> getComingLunches() throws IOException {
        List<Lunch> thisWeeksLunches = getLunchesFromExcel();
        List<Lunch> comingLunches = new ArrayList<>();
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        for(Lunch lunch : thisWeeksLunches){
            DayOfWeek lunchDay = getDayOfWeek(lunch.getDay());
            if(lunchDay.compareTo(today)>=0){
                comingLunches.add(lunch);
            }
        }
        return comingLunches;
    }


    public List<Lunch> getLunchesFromExcel() throws IOException {

        List<Lunch> list = new ArrayList<Lunch>();
        try (InputStream is = getClass().getResourceAsStream("/menu.xlsx")){
            assert is != null;
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet){
                if(row.getRowNum()==0){
                    continue;
                }
                String day = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();
                double price = row.getCell(3).getNumericCellValue();
                list.add(new Lunch(day,title,description, (int) price));
            }
        }
        return list;
    }

    private DayOfWeek getDayOfWeek(String day){
        switch (day.toLowerCase()){
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
    private String getDayString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "Måndag";
            case TUESDAY: return "Tisdag";
            case WEDNESDAY: return "Onsdag";
            case THURSDAY: return "Torsdag";
            case FRIDAY: return "Fredag";
            case SATURDAY: return "Lördag";
            case SUNDAY: return "Söndag";
            default: throw new IllegalArgumentException("Invalid day: " + dayOfWeek);
        }
    }
}

   /*
        Lunch lunch1 = new Lunch("Måndag", "Wallenbergare", "God!", 110);
        List<Lunch> list = new ArrayList<Lunch>();
        list.add(lunch1);
        list.add(lunch1);
        return list;
         */

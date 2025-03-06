package miun.dt170g.antons;

import java.util.Base64;

public class Event {
    private String event;
    private String date;
    private String time;
    private byte[] image;
    private String imageUrl;

    public Event(String event, String date, String time, byte[] image, String imageUrl) {
        this.event = event;
        this.date = date;
        this.time = time;
        this.image = image;
        this.imageUrl = imageUrl;

    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {this.time = time;}

    public byte[] getImage() {return image;}

    public void setImage(byte[] image) {this.image = image;}

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}

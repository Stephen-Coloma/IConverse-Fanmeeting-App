package shared;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Fanmeet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
    private int fanMeetID;
    private User idolName; // Reference to User class
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double pricePerMinute;
    private String status;
    public Fanmeet(){
        this.fanMeetID = 0;
        this.idolName = null;
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.pricePerMinute = 0.0;
    }

    // Constructor
    public Fanmeet(int fanMeetID, User idolName, LocalDate date, LocalTime startTime, LocalTime endTime, double pricePerMinute, String status) {
        this.fanMeetID = fanMeetID;
        this.idolName = idolName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pricePerMinute = pricePerMinute;
        this.status = status;
    }

    // Getters and setters
    public int getFanMeetID() {
        return fanMeetID;
    }

    public void setFanMeetID(int fanMeetID) {
        this.fanMeetID = fanMeetID;
    }
    public User getIdolName(){
        return idolName;
    }

    public void setIdolName(User user) {
        this.idolName = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getFormattedStartTime(){
        return startTime.format(formatter);
    }
    public String getFormattedEndTime(){
        return endTime.format(formatter);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
    public void setStatus(String status){
            this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

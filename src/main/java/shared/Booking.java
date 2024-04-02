package shared;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private int bookingID;
    private User userID;
    private Fanmeet fanMeetID;
    private LocalDateTime timeStamp;
    private LocalTime startTime;
    private int duration;
    private double price;
    private String status;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");

    public Booking(int bookingID, User userID, Fanmeet fanMeetID, LocalDateTime timeStamp, LocalTime startTime, int duration, double price, String status) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.fanMeetID = fanMeetID;
        this.timeStamp = timeStamp;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.status = status;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return this.bookingID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public User getUserID() {
        return this.userID;
    }

    public void setFanMeetID(Fanmeet fanMeetID) {
        this.fanMeetID = fanMeetID;
    }

    public Fanmeet getFanMeetID() {
        return this.fanMeetID;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getFormattedTimeStamp() {
        return this.timeStamp.format(DATE_TIME_FORMATTER);
    }

    public String getFormattedStartTime() {
        return this.startTime.format(TIME_FORMATTER);
    }
}

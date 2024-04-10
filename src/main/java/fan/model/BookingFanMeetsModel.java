package fan.model;

import fan.Fan;
import jdbc.FanJDBC;
import shared.Booking;
import shared.Fanmeet;
import shared.User;

public class BookingFanMeetsModel {
    private User idol;
    private Fanmeet fanmeet;

    public BookingFanMeetsModel(User idol, Fanmeet fanmeet) {
        this.idol = idol;
        this.fanmeet = fanmeet;
    }

    public void addBooking(Booking booking) {
        FanJDBC.addBookingToDB(booking);
    }

    public User getIdol() {
        return idol;
    }

    public Fanmeet getFanmeet() {
        return fanmeet;
    }
} // end of BookingFanMeetsModel class

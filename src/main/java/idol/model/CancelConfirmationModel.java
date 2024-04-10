package idol.model;

import shared.Booking;
import shared.Fanmeet;

public class CancelConfirmationModel {
    private Fanmeet fanmeet;
    private Booking booking;

    public CancelConfirmationModel(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }
    public CancelConfirmationModel(Booking booking) {
        this.booking = booking;
    }

    public Fanmeet getFanmeet() {
        return fanmeet;
    }

    public void setFanmeet(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

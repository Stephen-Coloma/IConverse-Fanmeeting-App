package fan.model;

import jdbc.FanJDBC;
import shared.Booking;

public class CancelBookingModel {
    private Booking booking;

    public CancelBookingModel(Booking booking) {
        this.booking = booking;
    }

    public void cancelBooking() {
        FanJDBC.cancelBooking(booking);
    } // end of cancelBooking
} // end of CancelBookingModel class

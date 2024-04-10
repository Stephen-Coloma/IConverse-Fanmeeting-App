package fan.model;

import shared.Booking;

public class FanVirtualMeetingModel {
    private Booking booking;

    public FanVirtualMeetingModel(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }
} // end of FanVirtualMeetingModel class

package shared;

public class Feedback {
    private User usersID;
    private Fanmeet fanMeetID;
    private String feedback;

    public Feedback(User usersID, Fanmeet fanMeetID, String feedback) {
        this.usersID = usersID;
        this.fanMeetID = fanMeetID;
        this.feedback = feedback;
    }

    public void setUsersID(User usersID) {
        this.usersID = usersID;
    }

    public User getUsersID() {
        return this.usersID;
    }

    public void setFanMeetID(Fanmeet fanMeetID) {
        this.fanMeetID = fanMeetID;
    }

    public Fanmeet getFanMeetID() {
        return this.fanMeetID;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return this.feedback;
    }
}

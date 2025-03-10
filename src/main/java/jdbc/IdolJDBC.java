package jdbc;

import fan.Fan;
import shared.Booking;
import shared.Fanmeet;
import shared.Feedback;
import shared.User;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**This class will be used to create methods that accesses the database solely intended for Idols*/
public class IdolJDBC {

    private static Connection connection;

    static {
        try {
            //changeable
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leonardos", "LeonardosAdmin", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String query;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static List<Fanmeet> loadFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();
        query = "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "FROM fanmeets " +
                "WHERE (IdolName = ? AND status = 'Finished') OR (IdolName = ? AND status IS NULL) " +
                "ORDER BY Date";

        preparedStatement = connection.prepareStatement(query);
        //java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        preparedStatement.setInt(1, userID);
        preparedStatement.setInt(2, userID);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Fanmeet fanmeet = new Fanmeet();

            fanmeet.setFanMeetID(resultSet.getInt("FanMeetID"));
            fanmeet.setDate(resultSet.getDate("Date").toLocalDate());
            fanmeet.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            fanmeet.setEndTime(resultSet.getTime("EndTime").toLocalTime());
            fanmeet.setPricePerMinute(resultSet.getDouble("PricePerMinute"));
            fanmeet.setStatus(resultSet.getString("Status"));

            fanmeetList.add(fanmeet);
        }
    return fanmeetList;
    }
    public static List<Fanmeet> loadUnFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();

        query= "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "from fanmeets " +
                "WHERE status = \"Unfinished\" " +
                "AND idolname = ? " +
                "ORDER BY Date";


        preparedStatement = connection.prepareStatement(query);
        //java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        preparedStatement.setInt(1, userID);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Fanmeet fanmeet = new Fanmeet();

            fanmeet.setFanMeetID(resultSet.getInt("FanMeetID"));
            fanmeet.setDate(resultSet.getDate("Date").toLocalDate());
            fanmeet.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            fanmeet.setEndTime(resultSet.getTime("EndTime").toLocalTime());
            fanmeet.setPricePerMinute(resultSet.getDouble("PricePerMinute"));
            fanmeet.setStatus(resultSet.getString("Status"));

            fanmeetList.add(fanmeet);

        }
        return fanmeetList;
    }

    public static List<Feedback> loadFeedbacks(int fanmeetID) throws Exception{
        query = "SELECT feedbacks.feedback, users.username, users.ProfilePicture " +
                "FROM feedbacks " +
                "INNER JOIN users on feedbacks.userID = users.userID " +
                "WHERE feedbacks.fanmeetID = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fanmeetID);
        resultSet = preparedStatement.executeQuery();

        List<Feedback> feedbackList = new ArrayList<>();
        while (resultSet.next()){
            String feedback = resultSet.getString("feedback");
            String username = resultSet.getString("username");
            Blob profilePicture = resultSet.getBlob("ProfilePicture");
            byte[] byteData = profilePicture.getBytes(1, (int) profilePicture.length());

            User user = new User(username, byteData);

            Feedback feedbackObject = new Feedback(user, feedback);

            feedbackList.add(feedbackObject);
        }

        return feedbackList;
    }
    public static HashMap<String, User> loadBookedFans(int fanMeetID) throws Exception{
        query = "SELECT users.Name, users.Username, users.Email, bookings.TimeStamp, users.ProfilePicture " +
                "FROM fanmeets " +
                "INNER JOIN bookings on fanmeets.FanMeetID = bookings.FanMeetID " +
                "INNER JOIN users on bookings.UserID = users.UserID " +
                "WHERE fanmeets.FanMeetID = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fanMeetID);
        resultSet = preparedStatement.executeQuery();

        HashMap<String, User> userTimeStampList = new HashMap<>();
        while (resultSet.next()){
            String timeStamp = resultSet.getTimestamp("TimeStamp").toString();

            User user = new User();
            user.setName(resultSet.getString("Name"));
            user.setUsername(resultSet.getString("Username"));
            user.setEmail(resultSet.getString("Email"));

            Blob profilePicture = resultSet.getBlob("ProfilePicture");
            byte[] byteArray = profilePicture.getBytes(1, (int) profilePicture.length());
            user.setProfilePicture(byteArray);

            userTimeStampList.put(timeStamp, user);
        }
        return userTimeStampList;
    }

    public static void addFanmeetToDB(Fanmeet fanmeet) throws Exception{
        query = "INSERT INTO fanmeets (IdolName, Date, StartTime, EndTime, PricePerMinute, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fanmeet.getIdolName().getUserID());
        preparedStatement.setDate(2, Date.valueOf(fanmeet.getDate()));
        preparedStatement.setTime(3, Time.valueOf(fanmeet.getStartTime()));
        preparedStatement.setTime(4, Time.valueOf(fanmeet.getEndTime()));
        preparedStatement.setDouble(5,fanmeet.getPricePerMinute());
        preparedStatement.setString(6, fanmeet.getStatus());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("A new fanmeet has been added successfully.");
        } else{
            System.err.println("Adding fanmeet failed.");
        }

    }

    public static void updateFanmeetDetails(Fanmeet fanmeet) throws Exception{
        //getting first the old start time of the fanmeet

        query =  " SELECT bookings.BookingID, bookings.StartTime AS BookingStartTime, fanmeets.StartTime AS FanMeetStartTime" +
                " FROM bookings" +
                " INNER JOIN fanmeets ON fanmeets.FanMeetID = bookings.FanMeetID" +
                " WHERE fanmeets.FanMeetID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fanmeet.getFanMeetID());
        resultSet = preparedStatement.executeQuery();

        List<Booking> affectedBookings = new ArrayList<>();
        LocalTime oldFanmeetStartTime = null;
        while (resultSet.next()){
            Booking booking = new Booking();
            booking.setBookingID(resultSet.getInt("BookingID"));
            booking.setStartTime(resultSet.getTime("BookingStartTime").toLocalTime());
            oldFanmeetStartTime = resultSet.getTime("FanMeetStartTime").toLocalTime();
            affectedBookings.add(booking);
        }

        System.out.println(affectedBookings);
        //updating now the fanmeet with its new date, start time and end time
        query = "UPDATE fanmeets "+
                "SET Date = ?, StartTime = ?, EndTime = ? "+
                "WHERE FanmeetID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(fanmeet.getDate()));
        preparedStatement.setTime(2, Time.valueOf(fanmeet.getStartTime()));
        preparedStatement.setTime(3, Time.valueOf(fanmeet.getEndTime()));
        preparedStatement.setInt(4, fanmeet.getFanMeetID());
        if(preparedStatement.executeUpdate()> 0){
            System.out.println("Fanmeet " +fanmeet.getFanMeetID() +" has been successfully updated");

        }else{
            System.out.println("Fanmeet failed to update");
        }

        //preparing the statement to be done
        query = "UPDATE bookings " +
                "SET StartTime = ? " +
                "WHERE BookingID = ?";


        //compute the minute difference of the booking start time to the original fanmeet start time and store it to a variable
        for (Booking booking: affectedBookings) {
            Duration duration = Duration.between(oldFanmeetStartTime, booking.getStartTime());
            long minuteDifference = duration.toMinutes();

            LocalTime newStartTimeForAffectedBookings = fanmeet.getStartTime().plusMinutes(minuteDifference);
            booking.setStartTime(newStartTimeForAffectedBookings);

            //updating the start time to the database
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTime(1, Time.valueOf(booking.getStartTime()));
            preparedStatement.setInt(2, booking.getBookingID());
            preparedStatement.executeUpdate();
        }
    }

    public static void cancelFanMeet(Fanmeet fanmeet){
        try {
            query = "UPDATE fanmeets " +
                    "SET Status = ? " +
                    "WHERE fanmeetID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setNull(1, Types.VARCHAR);
            preparedStatement.setInt(2, fanmeet.getFanMeetID());
            int temp1 = preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**For testing purposes only*/
    public static void main(String[] args) {

    }
}

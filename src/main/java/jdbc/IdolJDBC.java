package jdbc;

import fan.Fan;
import shared.Fanmeet;
import shared.Feedback;
import shared.User;

import java.sql.*;
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
        query= "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "from fanmeets " +
                "WHERE status = \"Finished\" " +
                "AND idolname = ?";

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
    public static List<Fanmeet> loadUnFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();

        query= "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "from fanmeets " +
                "WHERE status = \"Unfinished\" " +
                "AND idolname = ?";


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
    public static void addFanmeetToDB(int userID, LocalDate date, LocalTime startTime, LocalTime endTime, double pricePerMinute, String status) throws Exception{
        query = "INSERT INTO fanmeets (IdolName, Date, StartTime, EndTime, PricePerMinute, Status) " +
                "VALUES ('?', '?', '?', '?', '?', '?')";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userID);
        preparedStatement.setDate(2, Date.valueOf(date));
        preparedStatement.setDate(3, Date.valueOf(String.valueOf(startTime)));
        preparedStatement.setDate(4, Date.valueOf(String.valueOf(endTime)));
        preparedStatement.setDouble(5,pricePerMinute);
        preparedStatement.setString(6, status);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("A new fanmeet has been added successfully.");
        } else{
            System.err.println("Adding fanmeet failed.");
        }

    }
    public static void addFanmeetToDB(Fanmeet fanmeet) throws Exception{
        query = "INSERT INTO fanmeets (IdolName, Date, StartTime, EndTime, PricePerMinute, Status) " +
                "VALUES ('?', '?', '?', '?', '?', '?')";

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
        query = "UPDATE fanmeets "+
                "SET IdolName = ?, Date = ?, StartTime = ?, EndTime = ?, PricePerMinute = ?, Status = ? "+
                "WHERE FanmeetID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,fanmeet.getIdolName().getUserID());
        preparedStatement.setDate(2, Date.valueOf(fanmeet.getDate()));
        preparedStatement.setTime(3, Time.valueOf(fanmeet.getStartTime()));
        preparedStatement.setTime(4, Time.valueOf(fanmeet.getEndTime()));
        preparedStatement.setDouble(5, fanmeet.getPricePerMinute());
        preparedStatement.setString(6, fanmeet.getStatus());
        preparedStatement.setInt(7, fanmeet.getFanMeetID());
        if(preparedStatement.executeUpdate()> 0){
            System.out.println("Fanmeet " +fanmeet.getFanMeetID() +" has been successfully updated");

        }else{
            System.out.println("Fanmeet failed to update");
        }


    }
    /**For testing purposes only*/
    public static void main(String[] args) {

    }
}

package jdbc;

import fan.Fan;
import shared.Fanmeet;
import shared.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**This class will be used to create methods that accesses the database solely intended for Idols*/
public class IdolJDBC {

    private static Connection connection;

    static {
        try {
            //changeable
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leonardosmidterm", "root", null);
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
        query= "SELECT distinct(fanmeets.fanmeetID) , fanmeets.IdolName, fanmeets.Date, fanmeets.StartTime, fanmeets.EndTime, fanmeets.PricePerMinute " +
                "FROM fanmeets " +
                "LEFT JOIN bookings ON fanmeets.FanmeetID = bookings.fanmeetID " +
                "INNER JOIN users ON fanmeets.idolname = users.userid " +
                "WHERE (bookings.status = \"Finished\" or bookings.status IS NULL) " +
                "AND Idolname = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
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

            fanmeetList.add(fanmeet);

        }
    return fanmeetList;
    }
    public static List<Fanmeet> loadUnFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();

        query= "SELECT distinct(fanmeets.fanmeetID) , fanmeets.IdolName, fanmeets.Date, fanmeets.StartTime, fanmeets.EndTime, fanmeets.PricePerMinute " +
                "FROM fanmeets " +
                "LEFT JOIN bookings ON fanmeets.FanmeetID = bookings.fanmeetID " +
                "INNER JOIN users ON fanmeets.idolname = users.userid " +
                "WHERE (bookings.status = \"Unfinished\"  or bookings.status IS NULL) " +
                "AND Idolname = ?";


        PreparedStatement preparedStatement = connection.prepareStatement(query);
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

            fanmeetList.add(fanmeet);

        }
        return fanmeetList;
    }
    public static List<User> loadUsers()throws Exception {

        query = "SELECT *" +
                "FROM users";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        List<User> userList = new ArrayList<>();
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setUserID(resultSet.getInt("UserID"));
            user.setUsername(resultSet.getString("Username"));
            user.setName(resultSet.getString("Name"));
            user.setPassword(resultSet.getString("Password"));
            user.setEmail(resultSet.getString("Email"));
            user.setUserType(resultSet.getString("UserType"));
            user.setStatus(resultSet.getString("Status"));
            user.setBio(resultSet.getString("Bio"));
            user.setProfilePicture(resultSet.getBytes("ProfilePicture"));
            userList.add(user);
        }
        return userList;
    }

    /**TODO: MARIUSSSSSS THIS MAINM METHOD WILL BE YOUR TESTING METHOD*/
    public static void main(String[] args) {
        try {

            List<User> listOfUsers = loadUsers();
            for(User user : listOfUsers){
                List<Fanmeet> listOfFinishedFanmeets = loadFinishedFanmeets(user.getUserID());
                System.out.println("\nFINISHED BOOKING OF FANMEET of IDOLNAME " +user.getUserID());
                for(Fanmeet fanmeet: listOfFinishedFanmeets){

                    System.out.println("DATE: " +fanmeet.getDate());
                    System.out.println("STARTIME: "+fanmeet.getStartTime());
                    System.out.println("ENDTIME: "+fanmeet.getEndTime());
                    System.out.println("PRICE: " +fanmeet.getPricePerMinute());
                    System.out.println("=========================\n");
                }
                List<Fanmeet> listOfUnFinishedFanmeets = loadUnFinishedFanmeets(user.getUserID());
                System.out.println("\nUNFINISHED BOOKING OF FANMEET of IDOLNAME " +user.getUserID());
                for(Fanmeet fanmeet: listOfUnFinishedFanmeets){
                    System.out.println("DATE: " +fanmeet.getDate());
                    System.out.println("STARTIME: "+fanmeet.getStartTime());
                    System.out.println("ENDTIME: "+fanmeet.getEndTime());
                    System.out.println("PRICE: " +fanmeet.getPricePerMinute());
                    System.out.println("=========================\n");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

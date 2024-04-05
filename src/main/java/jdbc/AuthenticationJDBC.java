package jdbc;

import fan.Fan;
import shared.User;

import javax.xml.transform.dom.DOMResult;
import java.sql.*;
import java.util.concurrent.Callable;

public class AuthenticationJDBC {
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
    /**This method checks the signs up the user to the database*/
    public static void login(User userLogin ) throws Exception{
        query = "SELECT UserID, UserType FROM users WHERE Username = ? AND Password = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userLogin.getUsername());
        preparedStatement.setString(2, userLogin.getPassword());

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            if (resultSet.getString("UserType").equalsIgnoreCase("Fan")) {
                Fan.USER_ID = resultSet.getInt("UserID");
            } else {
                // TODO add the userID in the Idol class
            }
        } else {
            throw new Exception("Account Does Not Exist");
        }
    }

    /**This method checks the login of the user to the database*/
    public static synchronized void signUp(User userSignUp) throws Exception{
        //check if there are any username exists
        query = "SELECT username" +
                " FROM users" +
                " WHERE username = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userSignUp.getUsername());

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            throw new Exception("Account exists");
        }

        //if it reaches here, means there is no account for that existing username
        insertNewUser(userSignUp);
    }

    /**Helper method to the signup method*/
    private static void insertNewUser(User userSignUp) {
        try {
            query = "INSERT INTO users(Username, Name, Password, Bio, Email, UserType, Status, ProfilePicture)" +
                    " VALUES(?,?,?,?,?,?,?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userSignUp.getUsername());
            preparedStatement.setString(2, userSignUp.getName());
            preparedStatement.setString(3, userSignUp.getPassword());
            preparedStatement.setString(4, userSignUp.getBio());
            preparedStatement.setString(5, userSignUp.getEmail());
            preparedStatement.setString(6, userSignUp.getUserType());
            preparedStatement.setString(7, userSignUp.getStatus());
            preparedStatement.setBytes(8, userSignUp.getProfilePicture());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

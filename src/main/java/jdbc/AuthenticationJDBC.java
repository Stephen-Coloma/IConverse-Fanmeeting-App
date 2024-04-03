package jdbc;

import shared.User;

import javax.xml.transform.dom.DOMResult;
import java.sql.*;
import java.util.concurrent.Callable;

public class AuthenticationJDBC {
    private static Connection connection;

    static {
        try {
            //changeable
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leonardos", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String query;
    private static Statement statement;
    private static ResultSet resultSet;
    /**This method checks the signs up the user to the database*/
    public static void login(User userLogin ) throws Exception{
        String query = "SELECT Username, Password" +
                " FROM users" +
                " WHERE Username = ?" +
                " AND Password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userLogin.getUsername());
        preparedStatement.setString(2, userLogin.getPassword());

        System.out.println(userLogin.getUsername());
        System.out.println(userLogin.getPassword());

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            //todo: implement the loading of the main menu
            System.out.println("LOGIN SUCCESSFUL!!!");
        }
    }

    /**This method checks the login of the user to the database*/
    public static void signUp(User userSignUp) throws Exception{
        //todo: implement the method
    }
}

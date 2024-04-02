package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**This class will be used to create methods that accesses the database solely intended for Idols*/
public class FanJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/leonardos", //editable
                    "root",//editable
                    "password"//editable
            );

            String query = "SELECT * FROM USERS";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println(resultSet.getString("Username"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

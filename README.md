# Leonardos

## Configuration
Before running the application, make sure to create a database in your MySql and import the sql file that we submitted.
Filename: leonardos.sql

MySQL Connection Configuration
1. In classes AuthenticationJDBC, FanJDBC and IdolJDBC make sure to use the appropriate parameters to make a connection to the MySQL.
2. Default values are:
    a. schema/database = jdbc:mysql://127.0.0.1:3306/leonardos
    b. user = LeonardosAdmin
    c. password = password 
3. Class Directories: src/main/java/jdbc

Note! Make sure to match the parameters on the values that you set in your MySQL.
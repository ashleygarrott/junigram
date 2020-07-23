package com.example.demo.security;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    Connection conn;
    Statement statement;
    public UserService() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ooc_hw3_db", "nessie", "howareyou1");
        statement = conn.createStatement();
    }


    public boolean checkIfUserExists(String username) throws SQLException {
        String sql = String.format("SELECT username FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        return result.next();
    }

    public int addUser(String username, String password) throws SQLException {
        String sql = String.format("INSERT INTO users VALUES (\'%s\', \'%s\')", username, password);
        return statement.executeUpdate(sql);
    }

    public int removeUser(String username) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE username = '%s'", username);
        return statement.executeUpdate(sql);
    }

    public int changeUsername(String username, String newUsername) throws SQLException {
        String sql = String.format("UPDATE users SET username = '%s' WHERE username = '%s'", newUsername, username);
        return statement.executeUpdate(sql);
    }

    public int changePassword(String username, String newPassword) throws SQLException {
        String sql = String.format("UPDATE users SET password = '%s' WHERE username = '%s'", newPassword, username);
        return statement.executeUpdate(sql);
    }

    public String getPassword(String username) throws SQLException {
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()){
            return result.getString("password");
        }
        else{
            return null;
        }
    }

    public List<String> getAllUsers() throws SQLException {
        List<String> usernames = new ArrayList<>();
        String sql = String.format("SELECT username FROM users");
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            String username = result.getString("username");
            usernames.add(username);
        }
        return usernames;
    }


    public boolean isValidCredentials(String username, String password) throws SQLException {
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            System.out.println(sql);
            String dbUsername = result.getString("username");
            String dbPassword = result.getString("password");
            System.out.println(dbUsername + " " + username);
            System.out.println(dbPassword + " " + password);
            return username.equals(dbUsername) && password.equals(dbPassword);
        }
        else{
            return false;
        }



    }

}

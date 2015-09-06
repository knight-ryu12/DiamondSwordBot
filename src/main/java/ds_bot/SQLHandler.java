package ds_bot;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by ryu on 15/08/16.
 * With Help Of CrystalMare. <- TY :)
 */
public class SQLHandler {

    public Properties properties;

    private String url, username, password;

    public SQLHandler() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/diamond.properties"));
            url = properties.getProperty("sql.url");
            username = properties.getProperty("sql.user");
            password = properties.getProperty("sql.pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection open() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return DriverManager.getConnection(url, username, password);
    }

    public String getWhoami(String hostname,String Sender) {
        Connection connection = null;
        String whoami = "Let's see, Oh wait.. I don't know you.";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT whoami " +
                            "FROM irc_user " +
                            "WHERE hostname = ?;"
            );

            ps.setString(1, hostname);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                whoami =  rs.getString("whoami");
                whoami = whoami.replace("%nick%",Sender);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return whoami;
    }

    public String getCharName(String Charname) {
        Connection connection = null;
        String Name = "Done!";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT Name " +
                            "FROM IRC_RP " +
                            "WHERE Name = ?;"
            );

            ps.setString(1, Charname);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Name;
    }

    public String setAll(String Charname, int HP, String Status) {
        Connection connection = null;
        String Result = "Done!";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO IRC_RP (Name, HP, Status)" +
                            " VALUES (?,?,?)"
            );

            ps.setString(1, Charname);
            ps.setInt(2, HP);
            ps.setString(3, Status);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Result;
    }
}

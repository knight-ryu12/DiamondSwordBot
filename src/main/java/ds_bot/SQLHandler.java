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
    public String Result[] = new String[7];

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
        String Name = "Not Found. Make sure your character is made.";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT Name " +
                            "FROM IRC_RP_DB " +
                            "WHERE IRChost = ?;"
            );

            ps.setString(1, Charname);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                Name = rs.getString("Name");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Name;
    }

    public String getHP(String Charname) {
        Connection connection = null;
        String Name = "Not Found. Make sure your character is made.";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT HP " +
                            "FROM IRC_RP_DB " +
                            "WHERE IRChost = ?;"
            );

            ps.setString(1, Charname);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                Name = rs.getString("HP");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Name;
    }

    public String setHP(String Charname) {
        Connection connection = null;
        String Name = "Not Found. Make sure your character is made.";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT HP " +
                            "FROM IRC_RP_DB " +
                            "WHERE IRChost = ?;"
            );

            ps.setString(1, Charname);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                Name = rs.getString("HP");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Name;
    }

    public String setAll(String Charname, int HP, String Status, String Hostname, String Weak, String Absorb, String Resist, int Level) {
        Connection connection = null;
        String Result = "Done!";
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO IRC_RP_DB (Name, HP, Status, IRChost, Weakness, Absoption, Resistence,Level)  " +
                            " VALUES (?,?,?,?,?,?,?,?);"
            );

            ps.setString(1, Charname);
            ps.setInt(2, HP);
            ps.setString(3, Status);
            ps.setString(4, Hostname);
            ps.setString(5, Weak);
            ps.setString(6, Absorb);
            ps.setString(7, Resist);
            ps.setInt(8, Level);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Result;
    }

    public String[] getstat(String Hostname) {
        Connection connection = null;
        //String Result[] = new String[5];
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT *" +
                            " FROM IRC_RP_DB" +
                            " WHERE IRChost = ?;"
            );
            ps.setString(1, Hostname);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Result[0] = rs.getString("Name");
            Result[1] = String.valueOf(rs.getInt("HP"));
            Result[2] = rs.getString("Status");
            Result[3] = rs.getString("Weakness");
            Result[4] = rs.getString("Absoption");
            Result[5] = rs.getString("Resistence");
            Result[6] = String.valueOf(rs.getInt("Level"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return Result;

    }
}


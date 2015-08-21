package ds_bot;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by ryu on 15/08/16.
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

    public Connection open() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

}

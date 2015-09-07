import ds_bot.SQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ryu on 15/08/16.
 */
public class TestSQL {

    public static final String HOSTNAME = "CrystalMare!crystal@bronyville.me";

    public static void main(String[] args) throws SQLException {
        SQLHandler handler = new SQLHandler();
        Connection connection = handler.open();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT whoami " +
                        "FROM irc_user " +
                        "WHERE hostname = ?;"
        );

        ps.setString(1, HOSTNAME);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            //user does not have whoami
        }
        String whoami = rs.getString("whoami");
        System.out.println(whoami);

    }

}

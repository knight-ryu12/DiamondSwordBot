package ds_bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

/**
 * Created by ryu on 15/09/07.
 */
public class MainRyu {
    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        //API API = new API();
        SQLHandler handler = new SQLHandler();
        String password = handler.properties.getProperty("Password");
        Configuration configuration = new Configuration.Builder()
                //.setName("")//Set the nick of the bot. CHANGE IN YOUR COD
                .setServer("ipv4.epickitty.uk", 4321, password)
                .setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
                        //.addCapHandler(new SASLCapHandler("RyuBot",password))
                .setLogin("ryu13212/Bot")
                .setRealName("Chromaryu bot!")
                .setFinger("RyuBot Finger.")
                        //.setServerPort(4321)
                        //
                        //.setAutoNickChange(true)//Join the espernet network
                        //.addAutoJoinChannel("#Creatiria")//Join the Friends channel
                .addListener(new Diamond())
                .addListener(new Adventure())//Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();
        // Before use bot. I need my Nickname as current. so it'll see My hostmask. never change.

    }
}

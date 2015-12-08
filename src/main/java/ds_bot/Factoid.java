package ds_bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by chroma on 15/12/07.
 */
public class Factoid extends ListenerAdapter {
    SQLHandler handler = new SQLHandler();

    @Override
    public void onMessage(MessageEvent event) {
        if (event.getMessage().startsWith("?addfactoid")) {
            if (event.getMessage().length() >= 11) {
                String[] factoidStr = event.getMessage().split(" ");
                if (event.getChannel().getOps().toString().contains(event.getUser().getNick()) || event.getUser().getHostmask().equals("ArcadiaWorld.tk")) {
                    try {
                        handler.addFactoid(factoidStr[1], factoidStr[2]);
                        event.getChannel().send().message("Added Factoid called " + factoidStr[1] + " with " + factoidStr[2]);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        event.respond("Check Augments");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        event.respond("Usage: addfactoid <factoidName> <Message> - Add factoid to Database");
                    }
                } else event.respond("You are not able to use this command. Ask Bot Admin");
            } else event.respond("Usage: addfactoid <factoidName> <Message> - Add factoid to Database");
        }
        if (event.getMessage().startsWith("?delfactoid")) {
            if (event.getMessage().length() >= 11) {
                String[] factoidStr = event.getMessage().split(" ");
                if (event.getChannel().getOps().toString().contains(event.getUser().getNick()) || event.getUser().getHostmask().equals("ArcadiaWorld.tk")) {
                    try {
                        handler.delFactoid(factoidStr[1]);
                        event.getChannel().send().message("Deleted Factoid called " + factoidStr[1]);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        event.respond("Check Augments");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        event.respond("Usage: delfactoid <facotidName> - Delete factoid from database");
                    }
                } else event.respond("You are not able to use this command. Ask Bot Admin");
            } else event.respond("Usage: delfactoid <facotidName> - Delete factoid from database");
        }
        if (event.getMessage().startsWith("?factoid")) {
            if (event.getMessage().length() >= 8) {
                String[] factoidStr = event.getMessage().split(" ");
                try {
                    event.getChannel().send().message(handler.Factoid(factoidStr[1]));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    event.respond("Check Augments");
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    event.respond("Usage: factoid <facotidName> - Call factoid from database and show response");
                }
            } else event.respond("Usage: factoid <facotidName> - Call factoid from database and show response");
        }
    }
}


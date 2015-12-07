package ds_bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by chroma on 15/12/07.
 */
public class Factoid  extends ListenerAdapter{
    SQLHandler handler = new SQLHandler();
    @Override
    public void onMessage(MessageEvent event) {
        if(event.getMessage().startsWith("?factoid")) {
            if(event.getMessage().length() >= 8) {
                String[] factoidStr = event.getMessage().split(" ");
                if(event.getChannel().getOps().toString().contains(event.getUser().getNick()) || event.getUser().getHostmask().equals("ArcadiaWorld.tk")) {
                    try {
                        handler.addFactoid(factoidStr[1], factoidStr[2]);
                        event.getChannel().send().message("Added Factoid called " + factoidStr[1] + " with " + factoidStr[2]);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        event.respond("Check Augments");
                    }
                }
            }
        }
        
    }
}

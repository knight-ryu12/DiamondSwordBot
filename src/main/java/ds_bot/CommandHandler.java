package ds_bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by kuroma on 15/10/01.
 */
public class CommandHandler extends ListenerAdapter {
    SQLHandler handler = new SQLHandler();

    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith("?AddCmd")) if (event.getMessage().length() >= 8) {
            String[] Split = event.getMessage().split(" ");
            event.getChannel().send().message(Split[1] + Split[2] + Split[3]);
            try {
                handler.addcommand(Integer.parseInt(Split[1]), Split[2], Split[3]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                event.getChannel().send().message("Argument Incorrect");
            }
        } else event.getChannel().send().message("Argument Incorrect or Not Given");
        /*if (event.getMessage().startsWith("?")) {
            String Cmd = event.getMessage().substring(1);
            String Error = handler.getCommand(Cmd);
            if (Error.equals("OK")) {
                int perm = handler.getPerm(event.getUser().getHostmask());
                if (Integer.parseInt(handler.Result[0]) == 1) {
                    if (Integer.parseInt(handler.Result[1]) <= perm) {
                        event.getChannel().send().message(handler.Result[2]);
                    }
                }
            }
        }*/
    }
}

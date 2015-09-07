package ds_bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ryu on 15/09/06.
 */

public class Adventure extends ListenerAdapter {
    SQLHandler handler = new SQLHandler();
    API API = new API();

    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith("?roll")) {
            if (event.getMessage().length() >= 5) {
                String Sender = event.getUser().getNick();
                String args = event.getMessage().substring(6);
                //event.getChannel().send().message(args);
                String regex = "(\\d+)d(\\d+)([\\+-]\\d+)?$";
                try {
                    Matcher m = Pattern.compile(regex).matcher(args);
                    m.matches();
                    //event.getChannel().send().message(m.group(1));
                    //event.getChannel().send().message(m.group(2));
                    for (int i = 1; i <= Integer.parseInt(m.group(1)); i++) {

                        event.getChannel().send().message(String.valueOf(API.showRandomInteger(1, Integer.parseInt(m.group(2)))));
                    }
                } catch (IllegalStateException e) {
                    event.getChannel().send().message("Input string is must be xdy");
                }
                //boolean matches = m.matches();
                //event.getChannel().send().message(String.valueOf(matches));
                //event.getChannel().send().message(m.group(1));
                //event.getChannel().send().message(m.group(2));
                //event.getChannel().send().message(String.valueOf(API.showRandomInteger(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)))));


            } else {
                event.getChannel().send().message("No parameter Given.");
            }
        }
        if (event.getMessage().startsWith("?addchar")) {
            if (event.getMessage().length() >= 9) {
                String args = event.getMessage().substring(9);
                String Sender = event.getUser().getNick();
                String[] split = args.split(" ");
                //event.getChannel().send().message(split[0]);
                //event.getChannel().send().message(split[1]);
                //event.getChannel().send().message(split[2]);
                try {
                    String result = handler.setAll(split[0], Integer.parseInt(split[1]), split[2], Sender);
                    event.getChannel().send().message(result);
                } catch (IllegalStateException e) {
                    event.respond("Check Argument.");
                }
                event.respond("SQL Query Done.");
            } else {
                event.respond("Check Argument.");
            }


        }
        if (event.getMessage().startsWith("?getchar")) {
            //String args = event.getMessage().substring(9);
            String Sender = event.getUser().getNick();

            try {
                String result = handler.getCharName(Sender);
                event.getChannel().send().message(result);
            } catch (IllegalStateException e) {
                event.respond("Check Argument.");
                e.printStackTrace();
            }
        }


    }
    }



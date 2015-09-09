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
                String out = null;
                String args = event.getMessage().substring(6);
                StringBuffer sb = new StringBuffer();
                //event.getChannel().send().message(args);
                String regex = "(\\d+)d(\\d+)([\\+-]\\d+)?$";
                try {
                    Matcher m = Pattern.compile(regex).matcher(args);
                    m.matches();
                    //event.getChannel().send().message(m.group(1));
                    //event.getChannel().send().message(m.group(2));
                    for (int i = 1; i <= Integer.parseInt(m.group(1)); i++) {
                        sb.append(API.showRandomInteger(1, Integer.parseInt(m.group(2))));
                        if (i <= Integer.parseInt(m.group(1))) {
                            sb.append(" ");
                        }
                    }
                    event.getChannel().send().message(Sender + " Rolls " + m.group(1) + "," + m.group(2) + "Sided dice, result =" + sb.toString());
                } catch (IllegalStateException e) {
                    event.getChannel().send().message("Input string is must be Integer");
                    e.printStackTrace();
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
                String Sender = event.getUser().getHostmask();
                String[] split = args.split(" ");
                //event.getChannel().send().message(split[0]);
                //event.getChannel().send().message(split[1]);
                //event.getChannel().send().message(split[2]);
                try {
                    String result = handler.setAll(split[0], Integer.parseInt(split[1]), split[2], Sender, split[3], split[4], split[5], Integer.parseInt(split[6]));
                    event.getChannel().send().message(result);
                } catch (IllegalStateException e) {
                    event.respond("Check Argument.");
                }
                event.respond("SQL Query Done.");
            } else {
                event.respond("Check Argument.");
            }


        }
        if (event.getMessage().startsWith("?getcharname")) {
            //String args = event.getMessage().substring(9);
            String Sender = event.getUser().getHostmask();

            try {
                String result = handler.getCharName(Sender);
                event.getChannel().send().message(result);
            } catch (IllegalStateException e) {
                event.respond("Check Argument.");
                e.printStackTrace();
            }
        }
        if (event.getMessage().startsWith("?addDamage")) {
            String Sender = event.getUser().getHostmask();
            String result = null;
            String args = event.getMessage().substring(event.getMessage().length() + 1);
            try {
                result = handler.getHP(Sender);
            } catch (IllegalStateException e) {
                event.respond("Check Argument.");
                e.printStackTrace();
            }
            int dmgvalue = Integer.parseInt(result) - Integer.parseInt(args);

        }
        if (event.getMessage().startsWith("?getstat")) {
            for (int i = 0; i <= 6; i++) {
                handler.Result[i] = null;
            }
            String Sender = event.getUser().getHostmask();
            try {
                handler.getstat(Sender);
            } catch (IllegalStateException e) {
                event.respond("Check Argument.");
                e.printStackTrace();
            } finally {
                event.getChannel().send().message(
                        "Name: " + handler.Result[0] + " | "
                                + " HP: " + handler.Result[1] + " | "
                                + " Status: " + handler.Result[2] + " | "
                                + " Weakness: " + handler.Result[3] + " | "
                                + " Absorption: " + handler.Result[4] + " | "
                                + " Resistence: " + handler.Result[5] + " | "
                                + " Level: " + handler.Result[6] + " | "
                );
            }


        }


    }
    }



package ds_bot;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kuroma on 15/09/28.
 */
public class Emote extends ListenerAdapter {
    SQLHandler handler = new SQLHandler();
    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith("?say")) {
            if (event.getMessage().length() >= 4) {
                String msgsay = event.getMessage().substring(5);
                event.getChannel().send().message(msgsay);
            } else event.getChannel().send().message("No Parameter Given.");
        }
        if (event.getMessage().startsWith("?action")) {
            if (event.getMessage().length() >= 8) {
                String Action = event.getMessage().substring(8);
                event.getChannel().send().action(Action);
            } else event.getChannel().send().message("No Parameter Given.");
        }
        if (event.getMessage().startsWith("?poke")) {
            String sender = event.getUser().getNick();
            if (event.getMessage().length() >= 6) {
                String Poker = event.getMessage().substring(6);
                if (Poker.equals("rnduser")) {
                    ArrayList<User> users = new ArrayList<User>();
                    for (User user : event.getChannel().getUsers().asList()) users.add(user);
                    Random rand = new Random();
                    int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
                    event.getChannel().send().action("pokes" + " " + users.get(randomNum).getNick());
                } else event.getChannel().send().action("pokes" + " " + Poker);
            } else event.getChannel().send().message("No Parameter Given." + " " + sender);
        }
        if (event.getMessage().startsWith("?intro"))
            event.getChannel().send().message("Hi all! I am Bot. Built by Chromaryu. (Also known as ryu or whatever) Thanks to PircBotX! :D");
        if (event.getMessage().startsWith("?Hug")) {
            String sender = event.getUser().getNick();
            if (event.getMessage().length() >= 5) {
                String Hug = event.getMessage().substring(5);
                if (Hug.equals("rnduser")) {
                    ArrayList<User> users = new ArrayList<User>();
                    for (User user : event.getChannel().getUsers().asList()) users.add(user);
                    Random rand = new Random();
                    int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
                    event.getChannel().send().action("Hugs" + " " + users.get(randomNum).getNick());
                } else event.getChannel().send().action("Hugs" + " " + Hug);
            } else event.getChannel().send().message("No Parameter Given." + " " + sender);
        }
    }
    /*public void onJoin(JoinEvent event) throws Exception {
        String Join = event.getUser().getNick();
        String Hostmask = event.getUser().getHostmask();
        String Flag =handler.getWhoami(Hostmask, Join);
        if(Flag != null) {
            if (!Join.equals(event.getBot().getNick())) {
                handler.addwhoami(Hostmask, "Hi there! %nick%!");
            } else {

            }
        }
    }*/
}


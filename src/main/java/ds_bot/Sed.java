package ds_bot;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by chroma on 15/12/25.
 */
public class Sed extends ListenerAdapter{
    ArrayList<Channel> chanlist = new ArrayList<Channel>();
    ArrayList<User> userlist = new ArrayList<User>();
    ArrayList<String> oldmessage = new ArrayList<String>();
    String oldmessagebuffer;
    String oldmessagechannel;
    public void onMessage(MessageEvent event) {
        int test;
        if(event.getMessage().startsWith("?test4")) {
            test = 0;
        while(!chanlist.contains(event.getChannel())){
            test++;
        }
            event.respond("You are talking at" + " " + chanlist.get(test).getName() +".");
        }
        for(Channel chan : event.getBot().getUserChannelDao().getAllChannels().asList()) chanlist.add(chan);
        for(User user : event.getChannel().getUsers().asList()) userlist.add(user);
    }
}

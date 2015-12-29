package ds_bot;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    int test;
    int channel;
    String satisfied;
    public void onMessage(MessageEvent event) throws IOException {
        test = 0;
        if(event.getMessage().startsWith("s/")) {
            while(!event.getChannel().equals(chanlist.get(test))){
                channel++;
            }
            // chanlist.get(test).getName() for getting channel whatever IRC ppl do this command.
            //event.respond("You are talking at" + " " + chanlist.get(test).getName() +".");
            String[] commandString = {"/bin/bash","-c","echo",oldmessage.get(channel).toString()," | "," sed "," -e "," '",event.getMessage(),"'"};
            Process process = Runtime.getRuntime().exec(commandString);
            BufferedReader in = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line;
            while ((line = in.readLine()) != null) event.getChannel().send().message(line);
            in.close();
        }
        for(Channel chan : event.getBot().getUserChannelDao().getAllChannels().asList()) chanlist.add(chan);
        // passive get old message. and old user too.
        while(!event.getChannel().equals(chanlist.get(test))){
            test++;
        }
        if(!satisfied.contains("s/")) oldmessage.set(test, event.getMessage());
        //for(User user : event.getChannel().getUsers().asList()) userlist.add(user);
    }
}

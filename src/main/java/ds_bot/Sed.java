package ds_bot;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by chroma on 15/12/25.
 */
public class Sed extends ListenerAdapter{

    public void onMessage(MessageEvent event) {
        if(event.getMessage().startsWith("s/")) {
            ArrayList<Channel> chanlist = new ArrayList<Channel>();
            for(Channel chan : event.getBot().getUserChannelDao().getAllChannels().asList()) chanlist.add(chan);
            event.respond(chanlist.get(0).getName());


        }
    }
}

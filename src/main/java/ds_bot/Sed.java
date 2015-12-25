package ds_bot;

import org.pircbotx.Channel;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by chroma on 15/12/25.
 */
public class Sed {
    @Override
    public void onMessage(MessageEvent event) {
        if(event.getMessage().startsWith("s/")) {
            ArrayList<Channel> chanlist = new ArrayList<Channel>();
            for(Channel chan : event.getChannel()) chanlist.add(chan);
        }
    }
}

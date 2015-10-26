package ds_bot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.net.URL;

/**
 * Created by kuroma on 15/10/26.
 */
public class JSONParser extends ListenerAdapter {
    public void onMessage(MessageEvent event) throws Exception {
        if(event.getMessage().startsWith("?chuck")) {
            URL url = new URL("http://api.icndb.com/jokes/random/1");
            Object content = url.getContent();
            event.getChannel().send().message(content.toString());
            JsonObject jsonObject = new JsonParser().parse(content.toString()).getAsJsonObject();
            String Chuck = jsonObject.get("joke").getAsString();
            event.getChannel().send().message(Chuck);
        }
    }
}

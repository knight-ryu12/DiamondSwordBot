package ds_bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Diamond extends ListenerAdapter {
    //String Version = "Diamond_Sword(EdenBot_ryu) V4.8";
    SQLHandler handler = new SQLHandler();
    String Version = handler.properties.getProperty("version");
    String[] Motd = {"Hello There, How is going Everyone?", "My Pending Projects is RPG Game Project! Need More Staff (Currently I am only one :()", "If you are interested in that, Please PM me :)"};
    //API API = new API();
    // Rnd Method is no longer New file.o/
    API API = new API();
    String[] rrmsg = {"Grabs guns and fire to", "Grabs Sword and behead", "Grabs Magic wand and zap magic to", "Punches", "kicks", "Throws"};
    //Connection c = null;
    int mode;
    //Statement stmt = null;
    @Override
    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith("?helloworld"))
            event.respond("Hello world!"); // That Command Begins all!
        if (event.getMessage().startsWith("?time")) {
            Date date = new Date();
            event.respond("Current Bot Time is" + " " + date);
        }
        if (event.getMessage().equalsIgnoreCase("?memory")) {
            event.respond("I have" + " " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB of memory" + "," + " And " + "Using " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "MB" + ".");
        }
        if (event.getMessage().equalsIgnoreCase("?GC")) {
            // Only GC,maybe GC
            Runtime.getRuntime().gc();
            event.getChannel().send().message("I did GC :D");
        }
        if (event.getMessage().equalsIgnoreCase("?uptime")) {
            try {
                File file = new File("/proc/uptime");
                FileReader filereader = new FileReader(file);
                BufferedReader br = new BufferedReader(filereader);
                String str = br.readLine();
                while (str != null) {
                    //System.out.println(str);
                    event.respond(str);
                    str = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                event.respond("I am dead.");
                e.printStackTrace();
            } catch (IOException e) {
                event.respond("I am dead.");
                e.printStackTrace();
            }
        }
        if (event.getMessage().startsWith("?@cmd")) {
            String senderhost = event.getUser().getHostmask();
            if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("ArcadiaWorld.tk") || senderhost.equals("177.138.74.70")) {
                String sender = event.getUser().getNick();
                String regex = "rm * ";
                Pattern p = Pattern.compile(regex);
                //event.getChannel().send().message("" + Dir);
                if (event.getMessage().length() >= 6) {
                    String Dir = event.getMessage().substring(6);
                    Matcher m = p.matcher(Dir);
                    if (!m.find()) try {
                        //event.getChannel().send().message(command);
                        Process process = Runtime.getRuntime().exec(Dir);
                        BufferedReader in = new BufferedReader(new InputStreamReader(process
                                .getInputStream()));
                        String line;
                        while ((line = in.readLine()) != null) event.getChannel().send().message(line);
                        in.close();
                    } catch (IOException e) {
                        event.getChannel().send().message("Well. you did wrong :/");
                    }
                    else event.getChannel().send().message("You should not do that command");
                } else event.getChannel().send().message("No Parameter Given." + " " + sender);
            } else event.getChannel().send().message("You don't have permission to do that");
        }
        if (event.getMessage().startsWith("?test1")) {
            String sender = event.getUser().getNick();
            event.getUser().send().notice(Motd[0]);
            event.getUser().send().notice(Motd[1]);
            event.getUser().send().notice(Motd[2]);
        }
        //if (event.getMessage().startsWith("?test2")) {
        //    event.respond(event.getBot().getUserChannelDao().getAllChannels());
        //}
        if (event.getMessage().startsWith("?whoami")) {
            String senderhost = event.getUser().getHostmask();
            String Sender = event.getUser().getNick();
            event.getChannel().send().message(handler.getWhoami(senderhost, Sender));
        }
        if (event.getMessage().startsWith("?reverse")) {
            String cmd = event.getMessage();
            if (event.getMessage().length() >= 9) {
                String source = cmd.substring(9);
                event.getChannel().send().message(new StringBuilder(source).reverse().toString());
            } else event.getChannel().send().message("test message error");
        }
        if (event.getMessage().equalsIgnoreCase("?skillgen")) {
            String aCL, Gender = null, HP, Str, Con, Dex, Int, Wis, Cha;
            int Rndnum;
            String CL[] = {"Valkyrie", "Knight", "Samurai", "Barbarian", "Caveman", "Wizard", "Rogue", "Ranger", "Monk", "Priest", "Archeologist", "Healer", "Tourist"};
            Rndnum = API.showRandomInteger(1, 2);
            if (Rndnum == 1) Gender = "Male";
            if (Rndnum == 2) Gender = "Female";
            Rndnum = API.showRandomInteger(1, 13);
            if (CL[Rndnum].equals("Valkyrie")) Gender = "Female";
            aCL = CL[Rndnum];
            Rndnum = API.showRandomInteger(8, 14);
            HP = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Str = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Con = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Dex = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Int = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Wis = String.valueOf(Rndnum);
            Rndnum = API.showRandomInteger(4, 20);
            Cha = String.valueOf(Rndnum);
            event.getChannel().send().message(event.getUser().getNick() + ", You are now " + aCL + "!, and You are " + Gender + ", and you have" + " HP=" + HP + " Str=" + Str + " Con=" + Con + " Dex=" + Dex + " Int=" + Int + " Wis=" + Wis + " Cha=" + Cha + "!");
        }
        if (event.getMessage().equalsIgnoreCase("?isOP")) {
            boolean IsAnOp = event.getChannel().getOps().toString().contains(event.getUser().getNick());
            if (IsAnOp) event.getChannel().send().message("You have OP!");
            else event.getChannel().send().message("You don't have op.. yet");
        }
        if (event.getMessage().equalsIgnoreCase("?isvoice")) {
            boolean IsAVoice = event.getChannel().getVoices().toString().contains(event.getUser().getNick());
            if (IsAVoice) event.getChannel().send().message("You have voice!");
            else event.getChannel().send().message("You don't have voice!");
        }
        if (event.getMessage().equalsIgnoreCase("?rr")) {
            int num = (int) (Math.random() * 6);
            String act = rrmsg[num];
            String sender = event.getUser().getNick();
            event.getChannel().send().action(act + " " + sender);
        }
        if (event.getMessage().startsWith("?@raw")) {
            if (event.getUser().getHostmask().equals("ArcadiaWorld.tk")) {
                if (event.getMessage().length() >= 6) {
                    String RAW = event.getMessage().substring(6);
                    event.getBot().sendRaw().rawLine(RAW);
                } else event.respond("Check Arguments.");
            } else event.respond("You are not permitted to do this action.");
        }
        if (event.getMessage().equalsIgnoreCase("?o")) event.getChannel().send().message("o/");
        if (event.getMessage().startsWith("?version")) {
            event.getChannel().send().message(Version);
        }
        if (event.getMessage().startsWith("?botcm shutdown")) {
            String senderhost = event.getUser().getHostmask();
            if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("ArcadiaWorld.tk") || senderhost.equals("epickitty.uk")) {
                event.respond("Shutdown bot....");
                System.exit(0);
            } else
                event.getChannel().send().message("Well, You don't have permission to stop this bot. " + "Nice Try.");
        }
    }
}


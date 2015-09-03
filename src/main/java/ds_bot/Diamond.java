package ds_bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
// import org.pircbotx.hooks.types.GenericMessageEvent;


public class Diamond extends ListenerAdapter {
   //// String Version = "Diamond_Sword(EdenBot_ryu) V4.8";

    SQLHandler handler = new SQLHandler();
    String Version = handler.properties.getProperty("version");
    String Server = handler.properties.getProperty("Server");
    String[] Motd = {"Hello There, How is going Everyone?", "My Pending Projects is RPG Game Project! Need More Staff (Currently I am only one :()", "If you are interested in that, Please PM me :)"};
    //API API = new API();
    // Rnd Method is no longer New file.o/

    API API = new API();
    String[] rrmsg = {"Grab guns and fire to", "Grab Sword and behead", "Grab Magic wand and zap magic to", "Punches", "kicks", "Throws into void"};
    //Connection c = null;
    int mode;
    //Statement stmt = null;

    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        //API API = new API();

        Configuration configuration = new Configuration.Builder()
                .setName("Ryubot") //Set the nick of the bot. CHANGE IN YOUR CODE
                .setServerHostname("irc.esper.net")
                .setLogin("ryubot")
                .setRealName("Chromaryu bot!")
                .setFinger("RyuBot Finger.")
                        //.setServerPort(17667)
                .setAutoNickChange(true)//Join the espernet network
                .addAutoJoinChannel("#Creatiria")//Join the Friends channel
                .addListener(new Diamond()) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();
        // Before use bot. I need my Nickname as current. so it'll see My hostmask. never change.

    }
    @Override

    public void onMessage(MessageEvent event) throws Exception {
        //When someone says ?helloworld respond with "Hello World"

        if (event.getMessage().startsWith("?helloworld"))
            event.respond("Hello world!");
        if (event.getMessage().startsWith("?time")) {
            Date date = new Date();
            event.respond("Current Bot Time is" + " " + date);
        }
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
                } else {
                    event.getChannel().send().action("pokes" + " " + Poker);
                }
            } else {
                event.getChannel().send().message("No Parameter Given." + " " + sender);
            }
        }
        if (event.getMessage().startsWith("?intro")) {
            event.getChannel().send().message("Hi all! I am Bot. Built by Chromaryu. (Also known as ryu or whatever) Thanks to PircBotX! :D");
            //event.getChannel().send().message("Well. I need name! new Name :D just PM Dragon1 for info!");

        }
        if (event.getMessage().equalsIgnoreCase("?memory")) {
            event.respond("I have" + " " + (Runtime.getRuntime().totalMemory() / 1024) + "KB of memory" + "," + " And " + "Using " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 + "KB" + ".");
        }
        if (event.getMessage().equalsIgnoreCase("?GC")) {
            // Only GC,maybe GC
            Runtime.getRuntime().gc();
            event.getChannel().send().message("I did GC :D");
        }
        if (event.getMessage().equalsIgnoreCase("?PCinfo")) {
            try {
                File file = new File("/proc/version");
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
                System.out.println(e);
            } catch (IOException e) {
                event.respond("I am dead.");
                System.out.println(e);
            }

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
                System.out.println(e);
            } catch (IOException e) {
                event.respond("I am dead.");
                System.out.println(e);
            }
        }
        if (event.getMessage().startsWith("?@cmd")) {
            String senderhost = event.getUser().getHostmask();
            if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("icysword.ml")) {
                String sender = event.getUser().getNick();
                String regex = "rm -rf *";
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
                        while ((line = in.readLine()) != null) {
                            event.getChannel().send().message(line);
                        }
                        in.close();
                    } catch (IOException e) {
                        event.getChannel().send().message("Well. you did wrong :/");
                    }
                    else {
                        event.getChannel().send().message("You should not do that command");
                    }
                } else {
                    event.getChannel().send().message("No Parameter Given." + " " + sender);
                }
            } else {
                event.getChannel().send().message("You don't have permission to do that");
            }
        }
        if (event.getMessage().startsWith("?test1")) {
            String sender = event.getUser().getNick();
            event.getUser().send().notice(Motd[0]);
            event.getUser().send().notice(Motd[1]);
            event.getUser().send().notice(Motd[2]);
        }
        if (event.getMessage().startsWith("?whoami")) {
            String senderhost = event.getUser().getHostmask();
            String Sender = event.getUser().getNick();
            event.getChannel().send().message(handler.getWhoami(senderhost, Sender));
        }
        if (event.getMessage().startsWith("?reverse")) {
            String cmd = event.getMessage();
            if (event.getMessage().length() >= 9) {
                String source = cmd.substring(9);
                //event.getChannel().send().message(" ");
                for (String part : source.split(" "))
                    event.getChannel().send().message(new StringBuilder(part).reverse().toString());
            } else event.getChannel().send().message("test message error");
        }
        if (event.getMessage().equalsIgnoreCase("?randomuser")) {
            ArrayList<User> users = new ArrayList<User>();
            for (User user : event.getChannel().getUsers().asList())
                users.add(user); //event.getChannel().send().message(users.toString());
            Random rand = new Random();
            int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
            event.respond(users.get(randomNum).getNick());
        }
        if (event.getMessage().equalsIgnoreCase("?easter1")) {
            ArrayList<User> users = new ArrayList<User>();
            users.remove("EdenBot_ryu");
            for (User user : event.getChannel().getUsers().asList()) users.add(user);
            Random rand = new Random();
            event.respond(users.get(rand.nextInt((users.size() - 0) + 1)).getNick() + " " + "loves" + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + "," + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + "," + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + "," + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + "..." + " " + "slept" + " " + "with" + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + " " + "and" + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + " " + "and" + " " + "dreams" + " " + "about" + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick() + " " + "getting" + " " + "married" + " " + "to" + " " + users.get(rand.nextInt((users.size() - 0) + 1) + 0).getNick());
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
        if (event.getMessage().startsWith("?o")) {
            event.getChannel().send().message("o/");
        }
        if (event.getMessage().startsWith("?version")) {
            event.getChannel().send().message(Version);
        }
        if (event.getMessage().startsWith("?togglemode on")) {
            if (mode == 0) mode = 1;
            event.respond("Success!");
        }
        if (event.getMessage().startsWith("?togglemode off")) {
            if (mode == 1) mode = 0;
            event.respond("Success!");
        }
        switch (mode) {
            case 1:
                if (event.getMessage().startsWith("?dice")) {
                    String Sender = event.getUser().getNick();
                    if (event.getMessage().length() >= 6) {
                        String roll = event.getMessage().substring(6);
                        event.getChannel().send().message(roll);
                        String start = null;
                        if (start.matches("^\\d+d\\d+$")) {
                            start.split("d");
                        }
                        event.getChannel().send().message(start);
                        int astart = Integer.parseInt(String.valueOf(start));
                        String end = roll.substring(3);
                        int aend = Integer.parseInt(String.valueOf(end));
                        int rndnum = API.showRandomInteger(astart, aend);
                        event.getChannel().send().message("<" + Sender + "> rolls dice with" + astart + "d" + aend + ". results " + rndnum);

                    } else {
                        event.getChannel().send().message("No parameter Given");
                    }
                }
        }
        if (event.getMessage().startsWith("?botcm shutdown")) {
            String senderhost = event.getUser().getHostmask();

            if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("icysword.ml") || senderhost.equals("epickitty.uk")) {
                event.respond("Shutdown bot....");
                System.exit(0);
            } else {
                event.getChannel().send().message("Well, You don't have permission to stop this bot. " + "Nice Try.");
            }

        }


    }
}


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
    // Rnd Method is no longer New file.
    API API = new API();
    String[] rrmsg = {"Grab guns and fire to", "Grab Sword and behead", "Grab Magic wand and zap magic to", "Punches", "kicks", "Throws into void"};
    //Connection c = null;
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
                .addAutoJoinChannel("#epic")//Join the Friends channel
                .addListener(new Diamond()) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();
        // Before use bot. I need my Nickname as current. so it'll see My hostmask. never change.

    }

    /*public void onPrivateMessage(MessageEvent event) {
        event.getChannel().send().message(event.getMessage().toString());
        if(event.getMessage().startsWith("?say")) {
            if (event.getMessage().length() >= 4) {
                String msgsay = event.getMessage().substring(5);
                event.getChannel().send().message(msgsay);
            } else event.getChannel().send().message("No Parameter Given.");
        }
        if(event.getMessage().startsWith("?action")) {
            if (event.getMessage().length() >= 8) {
                String Action = event.getMessage().substring(8);
                event.getChannel().send().action(Action);
            } else event.getChannel().send().message("No Parameter Given.");
        }*/

    /*	public int getrnduser(MessageEvent event){
            ArrayList<User> users = new ArrayList<User>();
            for(User user : event.getChannel().getUsers().asList()) users.add(user);
            Random rand = new Random();
            int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
            return randomNum;
        }*/
    @Override

    public void onMessage(MessageEvent event) throws Exception {
        //When someone says ?helloworld respond with "Hello World"

        if (event.getMessage().startsWith("?helloworld"))
            event.respond("Hello world!");
        if (event.getMessage().startsWith("?time")) {
            Date date = new Date();
            event.respond("Current Bot Time is" + " " + date);
        }
        if (event.getMessage().startsWith("?rnd")) {
            Random rand = new Random();
            //String rndstr = String.valueOf((int) Math.floor(Math.random() * 10));
            String rndstr = String.valueOf(API.showRandomInteger(1, 65536));
            //String rndstr = API.rndstr();
            event.respond(rndstr);
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
                /*	if(event.getMessage().equalsIgnoreCase("?cat")){
                        //int Length = event.getMessage().length();
                    	String sender = event.getUser().getNick();
                    	if(event.getMessage().length() >= 5){
                    		try{
                    			String sub = event.getMessage().substring(6);
                    			event.respond(sub);
                    			File file = new File(sub);
                    			FileReader filereader = new FileReader(file);
                    			BufferedReader br = new BufferedReader(filereader);
                    			String str = br.readLine();
                    		while(str != null){
                    			//System.out.println(str);
                    			event.respond(str);
                    			str = br.readLine();
                    		}
                    	br.close();
                    	}catch(FileNotFoundException e){
                    		event.respond("I am dead.");
                    		//event.getChannel().send().message("I don't have that file. Maybe you need check path");
                    		System.out.println(e);
                    	}catch(IOException e){
                    		event.respond("I am dead.");
                    		System.out.println(e);
                    	}
                    	} else {
                    		event.getChannel().send().message("No Parameter Given." + " " + sender);
                    	}
                }*/
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
            //event.respond(senderhost);
            //event.getChannel().send().message();
            event.getChannel().send().message(handler.getWhoami(senderhost,Sender));

           // if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("icysword.ml")) {
            //    event.respond("Hey Owner! <3");
            //} else if (senderhost.equals("epickitty.uk")) {
            //    event.getChannel().send().message("woooo EpicKitty :D How are you?");
           // } else if (senderhost.equals("lucca.marumaru.ml")) {
           //     event.getChannel().send().message("I'll produces human/bot hybrid," + " " + Sender + "!");
            //} else if (senderhost.equals("yui.marumaru.ml")) {
            //    event.getChannel().send().message("He is badass monster slayer from the future," + Sender + "!");
            //} else if (senderhost.equals("Down.With.The.Bloody.Red.Queen.PanicBNC.ca")) {
            //    event.getChannel().send().message("Ah, the remarkably pleasant Dino-Chicken. How's the hair nowadays," + Sender + "?");
           // } else if (senderhost.equals("id-31009.charlton.irccloud.com")) {
            //    event.getChannel().send().message("He is Minecraft Server Owner, And Good and very kind to another server player." + " " + Sender + "!");
          //  } else if (senderhost.equals("pet.all.the.furries.ga")) {
           //     event.getChannel().send().message("He is blue balloonfox, " + Sender + "! " + "And it's round and cute :)");
           // } //else if (senderhost.equals("bronyville.me")) {
                //event.getChannel().send().message("Hi Crystal, what's up?");
            //} else {
            //    event.respond("uhhh I don't know you. But you're my friend now :P");
             //   event.getChannel().send().message("You can suggest custom comments.");
            //}
            // For Debug. event.respond("You are" + " " + Sender);
        }
        if (event.getMessage().startsWith("?reverse")) {
            String cmd = event.getMessage();
            //event.getChannel().send().message(cmd);
            //String source = cmd.substring(10);
            //event.getChannel().send().message(source);
            if (event.getMessage().length() >= 9) {
                String source = cmd.substring(9);

                for (String part : source.split(" ")) {
                    event.getChannel().send().message(new StringBuilder(part).reverse().toString());
                    //event.getChannel().send().message(" ");
                }
            } else {
                event.getChannel().send().message("test message error");
            }
        }
        if (event.getMessage().equalsIgnoreCase("?randomuser")) {
            ArrayList<User> users = new ArrayList<User>();
            for (User user : event.getChannel().getUsers().asList())
                users.add(user); //event.getChannel().send().message(users.toString());

            Random rand = new Random();
            int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
            event.respond(users.get(randomNum).getNick());
        }
        /*if (event.getMessage().startsWith("?@SQLop")) {

            String senderhost = event.getUser().getHostmask();
            if (senderhost.equals("j220156139067.nct9.ne.jp") || senderhost.equals("icysword.ml")) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:SQL.db");
                } catch (Exception e) {
                    event.getChannel().send().message(e.getClass().getName() + ": " + e.getMessage());

                }
                event.getChannel().send().message("Opened database successfully");
                try {
                    stmt = c.createStatement();
                    String Sql = event.getMessage().substring(8);
                    stmt.executeUpdate(Sql);
                    c.commit();
                    stmt.close();
                    c.close();
                    event.getChannel().send().message("Operation Sccessfull.");
                } catch (Exception e) {
                    event.getChannel().send().message(e.getClass().getName() + ": " + e.getMessage());

                }

            }
        }*/

        if (event.getMessage().equalsIgnoreCase("?easter1")) {
            ArrayList<User> users = new ArrayList<User>();
            users.remove("EdenBot_ryu");
            for (User user : event.getChannel().getUsers().asList()) users.add(user);
            Random rand = new Random();
            //int randomNum = rand.nextInt((users.size() - 0) + 1) + 0;
            //event.respond(users.get(randomNum).getNick());
            //event.getChannel().send();
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
                	/*ArrayList<User> users = new ArrayList<User>();
                    String sender = event.getUser().getNick();
                	for(User OP : event.getChannel().getOps()){
                		users.add(OP);

                		event.getChannel().send().message(OP.toString());
                	}
                	event.getChannel().send().message(sender);
                	/*boolean opuser =
                	if(opuser == true) {
                		event.getChannel().send().message("You have OP!");
                	} else {
                		event.getChannel().send().message("You don't have OP. yet.");
                	}*/
                	/*User userPrefix = event.getUser().getPrefix();
                    ArrayList<User> users = new ArrayList<User>();
                	if (userPrefix == "@") {
                		event.getChannel().send().message("You have OP!");
                		users.add(event.getUser().getNick());
                	}
                 else {
                	event.getChannel().send().message("You do not have OP. yet.");
                }*/

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
              /*  if(event.getMessage().equalsIgnoreCase("?dir")){
                	if(event.getMessage().length() >= 4){
                String path = event.getMessage().toString().substring(5);
                 event.respond(path);
                File dir = new File(path);
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    event.getChannel().send().message((i + 1) + ":    " + file);
                }
                	} else {
                		event.getChannel().send().message("well...");
                	}
                }*/
        if (event.getMessage().startsWith("?o")) {
            event.getChannel().send().message("o/");
        }
        if (event.getMessage().startsWith("?version")) {
            event.getChannel().send().message(Version);
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


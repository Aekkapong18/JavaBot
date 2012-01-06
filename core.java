import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

class core {
	public static void main(String args[]){
		Socket s;
		BlockingQueue<String> readQ;
		BufferedReader in;
		PrintWriter out;
		String current;
		try {
			s = new Socket("irc.andirc.net", 6667);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			out.println("USER Ewok krypton.zifnab06.net AndIRC :Doesn't Own Pants");
			out.println("NICK Siri");
			Thread.sleep(1000);
			out.println("MODE siri -x");
			out.println("JOIN #thunderbolt");
			out.println("JOIN #gnexus");
			Thread.sleep(1000);
			out.println("JOIN #bionic");
			out.println("JOIN #rezound");
			Thread.sleep(1000);
			out.println("JOIN #charge");
			out.println("JOIN #hideout");

			run(s,in,out);
			} catch (Exception e){e.printStackTrace();}
		}
	public static void run(Socket s, BufferedReader in, PrintWriter out){
		String current;
		while (true) {
			try {
			current = in.readLine();
				if (current == null) System.exit(0);
				System.out.println(current);
				switch(current.split(" ")[0]){
					case "PING":
						System.out.println("PONG sent");
						out.println("PONG " + current.split(" ")[1]);
						break;
					default:
						switch(current.split(" ")[1]){
							//$nick PRIVMSG $channel :$msg
							//$nick PRIVMSG $channel :Siri, command excess
							case "PRIVMSG":
								if(current.toLowerCase().split(" ")[3].contains("siri")){
									switch(current.toLowerCase().split(" ")[4]){
									case "google":
										String searchTerms = "";
										searchTerms = current.toLowerCase().split("google ")[1];
										if(!searchTerms.equals(""))
											out.println("PRIVMSG #" + current.toLowerCase().split("#")[1].split(" ")[0] + " :" + google.search(searchTerms)[1]);
										break;
									case "join":
										out.println("JOIN " + current.toLowerCase().split("join ")[1]);
										break;
									case "source":
										out.println("PRIVMSG #" + current.toLowerCase().split("#")[1].split(" ")[0] + " :http://github.com/zifnab06/JavaBot");
										break;
									}
								}
								break;
							default:
								break;
						}
					break;
				}
			} catch (Exception e){e.printStackTrace();}
		}
	}
}

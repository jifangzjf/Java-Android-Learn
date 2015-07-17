import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/*
 * 子线程:
 * 	接受来自client的消息
 *  将其根据list的内容广播出去 
 */
public class ServerRunnable implements Runnable {

	private List<Socket> clientList = null;
	private Socket client = null;

	public ServerRunnable(List<Socket> clientList, Socket client) {
		this.clientList = clientList;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				for (Socket aClient : clientList) {
					PrintStream printer = new PrintStream(
							aClient.getOutputStream());
					printer.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientList.remove(client);
		}
	}
}

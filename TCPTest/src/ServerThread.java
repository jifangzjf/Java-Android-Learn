import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket client;
	private OutputStream os;
	private InputStream is;

	public ServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			os = client.getOutputStream();
			is = client.getInputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = is.read(buffer)) != -1) {
				System.out.println("Server: " + new String(buffer, 0, count));
				os.write(buffer, 0, count);
			}
			os.close();
			is.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

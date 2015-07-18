import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 主线程:
 *  建立连接
 * 	从键盘读取数据, 发送到服务器
 */
public class Client {

	public static void main(String[] args) {
		Socket socket = new Socket();
		BufferedReader reader = null;
		PrintStream writer = null;
		try {
			socket.connect(
					new InetSocketAddress(InetAddress.getByName("127.0.0.1"),
							8001), 1000);
			// 开启读取数据线程
			new Thread(new ClientRunnable(socket)).start();
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintStream(socket.getOutputStream());

			// 从键盘读取数据
			String line = null;
			while ((line = reader.readLine()) != null) {
				// 写到服务器
				writer.println(line);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

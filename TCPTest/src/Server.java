import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * 主线程:
 * 	Server接受来自client的连接
 * 	加入list, 并启动处理线程
 */
public class Server {
	private static List<Socket> clientList = Collections
			.synchronizedList(new LinkedList<Socket>());

	public static void main(String[] args) {
		ServerSocket socket;
		Socket client;
		try {
			// 创建监听Socket
			socket = new ServerSocket(8001);
			System.out.println(socket.toString() + "\n服务器已经启动...");
			while (true) {
				// 接受连接
				client = socket.accept();
				System.out.println("Server Accept");
				// 加入client队列
				clientList.add(client);
				// 启动client处理线程
				new Thread(new ServerRunnable(clientList, client)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Server 接收 Client发送过来的数据
 * 打印到屏幕
 * 给Client反馈
 */
public class Server {

	public static void main(String[] args) {
		ServerSocket socket;
		Socket client;
		try {
			socket = new ServerSocket(8002);
			socket.setReuseAddress(true);
			System.out.println(socket.toString() + " 服务器已经启动...");
			while (true) {
				client = socket.accept();
				System.out.println("Server Accept");
				new ServerThread(client).start();
				System.out.println("Server Thread started");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

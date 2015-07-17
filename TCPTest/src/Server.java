import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * ���߳�:
 * 	Server��������client������
 * 	����list, �����������߳�
 */
public class Server {
	private static List<Socket> clientList = Collections
			.synchronizedList(new LinkedList<Socket>());

	public static void main(String[] args) {
		ServerSocket socket;
		Socket client;
		try {
			// ��������Socket
			socket = new ServerSocket(8001);
			System.out.println(socket.toString() + "\n�������Ѿ�����...");
			while (true) {
				// ��������
				client = socket.accept();
				System.out.println("Server Accept");
				// ����client����
				clientList.add(client);
				// ����client�����߳�
				new Thread(new ServerRunnable(clientList, client)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

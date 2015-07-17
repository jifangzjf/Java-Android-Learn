import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Server ���� Client���͹���������
 * ��ӡ����Ļ
 * ��Client����
 */
public class Server {

	public static void main(String[] args) {
		ServerSocket socket;
		Socket client;
		try {
			socket = new ServerSocket(8002);
			socket.setReuseAddress(true);
			System.out.println(socket.toString() + " �������Ѿ�����...");
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

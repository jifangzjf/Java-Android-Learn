import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * Server����Client���͵�����
 * ��ӡ����Ļ
 * ���ͷ���"OK"
 */
public class Server {
	public static void main(String[] args) {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		byte[] data = new byte[1024];

		try {
			socket = new DatagramSocket(8003);
			System.out.println("������������...");
			while (true) {
				// ����client����
				packet = new DatagramPacket(data, data.length);
				socket.receive(packet);

				// ��ȡclient����
				data = packet.getData();
				System.out.println("message: "
						+ new String(data, 0, packet.getLength()));

				// ��ȡclient IP�Ͷ˿�
				InetAddress clientAddress = packet.getAddress();
				int clientPort = packet.getPort();
				System.out.println("IP: " + clientAddress.getHostName()
						+ ", port: " + clientPort);

				// ��client����
				packet = new DatagramPacket("OK".getBytes(),
						"OK".getBytes().length, clientAddress, clientPort);
				socket.send(packet);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}

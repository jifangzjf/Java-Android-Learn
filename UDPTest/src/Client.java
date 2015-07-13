import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

/*
 * Client���ͱ���ʱ�䵽������
 * ����Server��������ӡ
 */
public class Client {

	public static void main(String[] args) {
		Date date = new Date();
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		byte[] buf = new byte[1024];

		try {
			socket = new DatagramSocket();
			// ��Server��������
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			packet = new DatagramPacket(date.toString().getBytes(), date
					.toString().getBytes().length, serverAddress, 8003);
			socket.send(packet);

			// ����server����, ����ӡ����Ļ
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			buf = packet.getData();
			System.out.println("�������ķ���Ϊ: "
					+ new String(buf, 0, packet.getLength()));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}

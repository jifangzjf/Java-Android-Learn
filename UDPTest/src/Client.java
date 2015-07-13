import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

/*
 * Client发送本地时间到服务器
 * 接受Server反馈并打印
 */
public class Client {

	public static void main(String[] args) {
		Date date = new Date();
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		byte[] buf = new byte[1024];

		try {
			socket = new DatagramSocket();
			// 向Server发送数据
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			packet = new DatagramPacket(date.toString().getBytes(), date
					.toString().getBytes().length, serverAddress, 8003);
			socket.send(packet);

			// 接收server反馈, 并打印到屏幕
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			buf = packet.getData();
			System.out.println("服务器的反馈为: "
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

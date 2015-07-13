import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * Server接受Client传送的数据
 * 打印到屏幕
 * 发送反馈"OK"
 */
public class Server {
	public static void main(String[] args) {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		byte[] data = new byte[1024];

		try {
			socket = new DatagramSocket(8003);
			System.out.println("服务器已启动...");
			while (true) {
				// 接收client数据
				packet = new DatagramPacket(data, data.length);
				socket.receive(packet);

				// 获取client数据
				data = packet.getData();
				System.out.println("message: "
						+ new String(data, 0, packet.getLength()));

				// 获取client IP和端口
				InetAddress clientAddress = packet.getAddress();
				int clientPort = packet.getPort();
				System.out.println("IP: " + clientAddress.getHostName()
						+ ", port: " + clientPort);

				// 给client反馈
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

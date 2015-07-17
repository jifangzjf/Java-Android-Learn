import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * client:
 * 	1. 获取系统时间
 * 	2. 给Server发送消息
 *  3. 从Server接受反馈
 *  4. 将反馈打印到屏幕
 */
public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		Scanner in = new Scanner(System.in);

		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 8002);
			is = socket.getInputStream();
			os = socket.getOutputStream();

			String buffer;
			byte[] receiveData = new byte[1024];

			while (in.hasNext()) {
				buffer = in.next();
				os.write(buffer.getBytes(), 0, buffer.getBytes().length);
				int count = is.read(receiveData);
				System.out.println("client: "
						+ new String(receiveData, 0, count));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				socket.close();
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

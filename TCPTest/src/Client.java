import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/*
 * client:
 * 	1. ��ȡϵͳʱ��
 * 	2. ��Server������Ϣ
 *  3. ��Server���ܷ���
 *  4. ��������ӡ����Ļ
 */
public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		Date date = new Date();
		InputStream is = null;
		OutputStream os = null;

		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 8001);
			is = socket.getInputStream();
			os = socket.getOutputStream();

			os.write(date.toString().getBytes());
			byte[] buffer = new byte[1024];
			int length = is.read(buffer);
			System.out.println("�������ķ�����: " + new String(buffer, 0, length));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

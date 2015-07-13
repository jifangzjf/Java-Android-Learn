import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Server ���� Client���͹���������
 * ��ӡ����Ļ
 * ��Client����
 */
public class Server {

	public static void main(String[] args) {
		ServerSocket socket = null;
		Socket client = null;
		OutputStream os = null;
		InputStream is = null;
		byte[] buffer = new byte[1024];
		try {
			socket = new ServerSocket(8001);
			System.out.println("�������Ѿ�����...");
			while (true) {
				client = socket.accept();
				os = client.getOutputStream();
				is = client.getInputStream();

				int length = is.read(buffer);
				System.out.println("message: " + new String(buffer, 0, length));
				String anser = new String("OK");
				os.write(anser.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

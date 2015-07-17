import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * ���߳�:
 * 	�ӷ�������������
 *  ��ӡ����Ļ
 */
public class ClientRunnable implements Runnable {
	private Socket socket = null;

	public ClientRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println("Broadcast:" + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

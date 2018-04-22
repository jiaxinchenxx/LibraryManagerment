package server;

import java.net.*;

public class LbServer {
	public LbServer(int port, IOStrategy ios) { // ��������������߳���ִ��
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("server is ready");
			while (true) {
				Socket socket = ss.accept(); // ���������������  //������ѭ����������
				ios.service(socket); // ���������˵�socket���󴫵ݸ�
			} // ThreadSupport����
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}

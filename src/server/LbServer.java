package server;

import java.net.*;

public class LbServer {
	public LbServer(int port, IOStrategy ios) { // 这个方法将在主线程中执行
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("server is ready");
			while (true) {
				Socket socket = ss.accept(); // 负责接受连接请求，  //服务器循环接受请求
				ios.service(socket); // 将服务器端的socket对象传递给
			} // ThreadSupport对象
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}

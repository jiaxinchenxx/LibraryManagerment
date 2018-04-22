package server;

import java.io.*;

public interface IOStrategy {
	
	public void service(java.net.Socket socket) throws IOException;

}

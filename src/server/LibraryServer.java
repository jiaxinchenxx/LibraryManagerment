package server;



public class LibraryServer {
	
	public static void main(String[] args) {
		new LbServer(2223, new ThreadPoolSupport(new LibraryProtocol()));
	}

}

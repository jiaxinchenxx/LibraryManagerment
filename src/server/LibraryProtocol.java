package server;



import java.io.*;
import java.net.*;


public class LibraryProtocol implements IOStrategy {  //客户端协议实现

	public void service(Socket socket){
		try{
		LibraryMthd lm = new LibraryServerImpl();  //创建一个具体协议对象，其中封装功能函数
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		int command = -1;
		while (true) {  
			command = dis.readInt();  //从客户端读入指令号
			switch(command)
			{
			case 0:
				lm.Init(socket.getOutputStream());  //指令0则初始化
				break;
			case 1:
				lm.AddBook(socket.getInputStream(),socket.getOutputStream()); //指令1则添加图书
				break;
			case 2:
				lm.ChangeBook(socket.getOutputStream(),socket.getInputStream());//指令2则更改图书信息
			    break;
			case 3:
				lm.DeleteBook(socket.getInputStream(),socket.getOutputStream());//指令3则删除图书
				break;
			case 4:
				lm.SearchBook(socket.getInputStream(),socket.getOutputStream());//指令4则查询图书
			}
		}
		
	}
		catch(Exception e){
		//e.printStackTrace();
			System.out.println("client disconnected."); //服务结束的话Socket断开产生异常则打印连接断开
	}
		
		// TODO 自动生成的方法存根
		
	}

}

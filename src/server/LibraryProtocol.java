package server;



import java.io.*;
import java.net.*;


public class LibraryProtocol implements IOStrategy {  //�ͻ���Э��ʵ��

	public void service(Socket socket){
		try{
		LibraryMthd lm = new LibraryServerImpl();  //����һ������Э��������з�װ���ܺ���
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		int command = -1;
		while (true) {  
			command = dis.readInt();  //�ӿͻ��˶���ָ���
			switch(command)
			{
			case 0:
				lm.Init(socket.getOutputStream());  //ָ��0���ʼ��
				break;
			case 1:
				lm.AddBook(socket.getInputStream(),socket.getOutputStream()); //ָ��1�����ͼ��
				break;
			case 2:
				lm.ChangeBook(socket.getOutputStream(),socket.getInputStream());//ָ��2�����ͼ����Ϣ
			    break;
			case 3:
				lm.DeleteBook(socket.getInputStream(),socket.getOutputStream());//ָ��3��ɾ��ͼ��
				break;
			case 4:
				lm.SearchBook(socket.getInputStream(),socket.getOutputStream());//ָ��4���ѯͼ��
			}
		}
		
	}
		catch(Exception e){
		//e.printStackTrace();
			System.out.println("client disconnected."); //��������Ļ�Socket�Ͽ������쳣���ӡ���ӶϿ�
	}
		
		// TODO �Զ����ɵķ������
		
	}

}

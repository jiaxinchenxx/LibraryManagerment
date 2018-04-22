package base;

import graui.ClientFrame;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class LibraryClientIdea { //这个类是客户端协议模型类
	
	public Socket s;  //客户端协议中包含成员Socket
	public Vector<Book> list = new Vector<Book>(); //在本地维护一个图书信息表list
	public DataInputStream dis = null;  //协议读入流
	public DataOutputStream dos = null;  //协议写出流
	
	public LibraryClientIdea(String host, int port) throws Exception {  //构造函数，设置Socket，以及网络上的读入写出流
		s = new Socket(host, port); // 这个Socket对象创建完毕后何时销毁？
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		Init();
	}
	
	public void Init() throws IOException{ //列表初始化函数
		list.clear();  //清空列表
		InputStreamReader isr = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		int Count;
		dos.writeInt(0);  //向服务器发送0号指令，为初始化指令
		Count = dis.readInt();  //从服务器获取信息
		String str = "";
		for(int i=0;i<Count;i++)  //获取图书信息
		{
			str = br.readLine();  
			StringTokenizer st = new StringTokenizer(str, " "); // 字符串分割器类
			String ID = st.nextToken().trim();
			String Name = st.nextToken().trim();
			String Author = st.nextToken().trim();
			double Price = Double.parseDouble(st.nextToken().trim());
			list.add(new Book(ID,Name,Author,Price));
		}		
		
	}
	
	public void AddBook(String ID,String Name,String Author,String Price) throws IOException{
		dos.writeInt(1);       //添加图书信息实现协议方法， 向服务器发送1号指令
		dos.writeUTF(ID);
		dos.writeUTF(Name);
		dos.writeUTF(Author);
		dos.writeUTF(Price);
		int i = dis.readInt();  //从服务器读入结果，如果为0则该书已存在
		if(i == 0)
			JOptionPane.showMessageDialog(null,"Exsisted Book!");
		else  //如果不为0则加入成功
		{
			JOptionPane.showMessageDialog(null,"Add Successfully!");
			list.add(new Book(ID,Name,Author,Double.parseDouble(Price)));
		}
		
		
	}
	
	public void ChangeBook(String ID,String Name,String Author,String Price) throws IOException{
		dos.writeInt(2);    //更改信息实现协议方法， 向服务器发送2号指令
		dos.writeUTF(ID);
		dos.writeUTF(Name);
		dos.writeUTF(Author);
		dos.writeUTF(Price);
		int i = dis.readInt();   //从服务器读入结果，如果为0则该书不存在
		if(i == 0)
			JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
		else     //若该书存在并已更改，则返回不为0，此时服务器内容已经更改，只需要维护一下本地图书信息即可
		{
		    Book book = new Book(ID,"","",0.0);
		    for(i = 0;i<list.size();i++)
		    {
			    if(list.get(i).equals(book))  //遍历本地信息找到待改信息图书并更改信息
			    {
				    Book b = list.get(i);
				    b.setName(Name);
				    b.setAuthor(Author);
				    b.setPrice(Double.parseDouble(Price));
			        break;
			    }
		    }
		    JOptionPane.showMessageDialog(null,"Change Successfully!");
		}
		
	}
	
	public void DeleteBook(String ID) throws IOException{
		dos.writeInt(3);   //删除图书信息实现协议方法， 向服务器发送3号指令
		dos.writeUTF(ID);
		int i = dis.readInt();
		if(i == 0)  //从服务器读入结果，如果为0则该书不存在
			JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
		else  //若该书存在，则返回不为0，此时服务器端已经删除该图书，只需要维护本地信息即可
		{
			Book book = new Book(ID,"","",0.0);
			for(i = 0;i<list.size();i++)  //遍历图书并删除
			{
				if(list.get(i).equals(book))
				{
					list.remove(i);
					break;
				}
			}
			JOptionPane.showMessageDialog(null,"Delete Successfully!");
		}
	}
	
	public Book SearchBook(String ID) throws IOException{
		dos.writeInt(4);   //查询图书信息实现协议方法， 向服务器发送4号指令
		dos.writeUTF(ID);
		int i = dis.readInt();
		if(i == 0)   //从服务器读入结果，如果为0则该书不存在
			return null;
		else{     //若该书存在，则返回不为0，读取从服务器传回来的信息
		    String Name = dis.readUTF();
		    String Author = dis.readUTF();
		    double Price = dis.readDouble();
		    return (new Book(ID,Name,Author,Price));
		}
	}

}

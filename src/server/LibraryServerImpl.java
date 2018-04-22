package server;
import base.*;

import java.io.*;
import java.util.*;

public class LibraryServerImpl implements LibraryMthd {  //各种操作不能同时进行，所以加上同步函数声明
	
	public static String FileAddr = "D://Eclipse//LibraryServer//BookList.txt"; //服务器图书信息文件
	Vector<Book> list;      //每个线程维护一个图书信息列表，便于操作                                                                                       //存储地址
	
	
	synchronized public void GetInfo() throws IOException{  //获取文件中图书信息到一个列表中
		
		FileInputStream fis = new FileInputStream(FileAddr);
		InputStreamReader isr = new InputStreamReader(fis,"GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		while((str = br.readLine())!=null)  //循环读取
		{
			str = str.trim();
			if (str.length() == 0)
				continue; // 如果行数据是空，则继续读下一行
			StringTokenizer st = new StringTokenizer(str, " "); // 字符串分割器类
			String ID = st.nextToken().trim();
			String Name = st.nextToken().trim();
			String Author = st.nextToken().trim();
			double Price = Double.parseDouble(st.nextToken().trim());
			list.add(new Book(ID,Name,Author,Price));		 //添加图书信息到列表中	
		}
		
	}
	
	synchronized public void Init(OutputStream os) throws IOException{  //初始化函数
		list = new Vector<Book>();  //新建图书列表
		DataOutputStream dos = new DataOutputStream(os);
		OutputStreamWriter osw = new OutputStreamWriter(os,"GBK");
		PrintWriter pw = new PrintWriter(osw);
		list.clear();
		GetInfo();  //获取列表信息
		dos.writeInt(list.size());
		for(int i=0;i<list.size();i++)
		{
			pw.println(list.get(i));  //想客户端发送图书信息
			pw.flush();
		}
		
	}
	
	synchronized public void AddBook(InputStream is,OutputStream os) throws IOException {
		list.clear();   //添加图书信息，在对信息操作之前，重新维护一下图书列表
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "",Name = "",Author = "",Price = "";
		ID = dis.readUTF();
		Name = dis.readUTF();
		Author = dis.readUTF();
		Price = dis.readUTF();   //得到待添加的图书信息
		Book book = new Book(ID,Name,Author,Double.parseDouble(Price)); //新建图书
		if(Search(book))  //查看图书是否已存在
			dos.writeInt(0);
		else  //若不存在
		{
			list.add(book);  //加入到列表中
		    dos.writeInt(1);
		    File f = new File(FileAddr);  
		    FileWriter fw = null;
		    PrintWriter pw = null;
		    FileInputStream fis = new FileInputStream(FileAddr);  //
		    InputStreamReader isr = new InputStreamReader(fis,"GBK");
		    BufferedReader br = new BufferedReader(isr);
		    String str = "";
		    try{
			    fw = new FileWriter(FileAddr,true);
			    pw = new PrintWriter(fw);
			    pw.println(ID + " " + Name + " " + Author + " " + Price);
			    pw.flush();  //追加写入文件中
		    }catch(Exception e){
			    System.out.println("File Not Found!");
		    }  
		    pw.close();
		}
	}

	
	synchronized public void ChangeBook(OutputStream os, InputStream is) throws IOException {
		list.clear();  //更改图书信息前，重新维护列表
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "",Name = "",Author = "",Price = "";
		ID = dis.readUTF();  //获取待更改图书信息
		Name = dis.readUTF();
		Author = dis.readUTF();
		Price = dis.readUTF();
		Book book = new Book(ID,Name,Author,Double.parseDouble(Price));  //新建该图书
		if(Search(book))  //如果待更改信息存在
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)  //在列表中查找该图书
			{
				if(list.get(i).equals(book))
				{
					Book b = list.get(i);
					b.setAuthor(Author);  //找到后更改信息
					b.setID(ID);
					b.setName(Name);
					b.setPrice(Double.parseDouble(Price));
					break;
				}
			}
			File f = new File(FileAddr);
		    FileWriter fw = new FileWriter(f);  //而后将更改后的信息写回到文件之中
		    PrintWriter pw = new PrintWriter(fw);
			for(int i=0;i<list.size();i++)
			{
				pw.println(list.get(i));
				pw.flush();
			}
			pw.close();
		}
		else
			dos.writeInt(0);  //如果待更改信息没有找到，则返回0
		
	}

	
	synchronized public void DeleteBook(InputStream is,OutputStream os) throws IOException {
		list.clear();  //删除图书之前，重新维护图书列表信息
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "";
		ID = dis.readUTF();  //读入待删除信息
		Book book = new Book(ID,"","",0.0);
		if(Search(book))  //若该书存在
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)  //找到该书
			{
				if(list.get(i).equals(book))
				{
				    list.remove(i);  //删除
				    break;
				}
			}
			File f = new File(FileAddr);  //重新写回文件中
		    FileWriter fw = new FileWriter(f);
		    PrintWriter pw = new PrintWriter(fw);
			for(int i=0;i<list.size();i++)
			{
				pw.println(list.get(i));
				pw.flush();
			}
			pw.close();
		}
		else
			dos.writeInt(0);	 //若不存在 ，返回0
	}

	
	public void SearchBook(InputStream is,OutputStream os) throws IOException {
		list.clear(); //查找图书
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "";
		ID = dis.readUTF();  //获取待查找信息
		Book book = new Book(ID,"","",0.0);
		if(Search(book))  //图书存在
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).equals(book))
				{
					Book b = list.get(i);  //返回待查找信息
					dos.writeUTF(b.getName());
					dos.writeUTF(b.getAuthor());
					dos.writeDouble(b.getPrice());   
					break;
				}
			}
		}
		else  //若不存在，则返回0
			dos.writeInt(0);
		
	}
	
	public boolean Search(Book book) throws IOException{  //查看该书是否存在
		if(list.contains(book))
			return true;
		else
			return false;
	}

}

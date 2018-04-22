package server;
import base.*;

import java.io.*;
import java.util.*;

public class LibraryServerImpl implements LibraryMthd {  //���ֲ�������ͬʱ���У����Լ���ͬ����������
	
	public static String FileAddr = "D://Eclipse//LibraryServer//BookList.txt"; //������ͼ����Ϣ�ļ�
	Vector<Book> list;      //ÿ���߳�ά��һ��ͼ����Ϣ�б����ڲ���                                                                                       //�洢��ַ
	
	
	synchronized public void GetInfo() throws IOException{  //��ȡ�ļ���ͼ����Ϣ��һ���б���
		
		FileInputStream fis = new FileInputStream(FileAddr);
		InputStreamReader isr = new InputStreamReader(fis,"GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		while((str = br.readLine())!=null)  //ѭ����ȡ
		{
			str = str.trim();
			if (str.length() == 0)
				continue; // ����������ǿգ����������һ��
			StringTokenizer st = new StringTokenizer(str, " "); // �ַ����ָ�����
			String ID = st.nextToken().trim();
			String Name = st.nextToken().trim();
			String Author = st.nextToken().trim();
			double Price = Double.parseDouble(st.nextToken().trim());
			list.add(new Book(ID,Name,Author,Price));		 //���ͼ����Ϣ���б���	
		}
		
	}
	
	synchronized public void Init(OutputStream os) throws IOException{  //��ʼ������
		list = new Vector<Book>();  //�½�ͼ���б�
		DataOutputStream dos = new DataOutputStream(os);
		OutputStreamWriter osw = new OutputStreamWriter(os,"GBK");
		PrintWriter pw = new PrintWriter(osw);
		list.clear();
		GetInfo();  //��ȡ�б���Ϣ
		dos.writeInt(list.size());
		for(int i=0;i<list.size();i++)
		{
			pw.println(list.get(i));  //��ͻ��˷���ͼ����Ϣ
			pw.flush();
		}
		
	}
	
	synchronized public void AddBook(InputStream is,OutputStream os) throws IOException {
		list.clear();   //���ͼ����Ϣ���ڶ���Ϣ����֮ǰ������ά��һ��ͼ���б�
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "",Name = "",Author = "",Price = "";
		ID = dis.readUTF();
		Name = dis.readUTF();
		Author = dis.readUTF();
		Price = dis.readUTF();   //�õ�����ӵ�ͼ����Ϣ
		Book book = new Book(ID,Name,Author,Double.parseDouble(Price)); //�½�ͼ��
		if(Search(book))  //�鿴ͼ���Ƿ��Ѵ���
			dos.writeInt(0);
		else  //��������
		{
			list.add(book);  //���뵽�б���
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
			    pw.flush();  //׷��д���ļ���
		    }catch(Exception e){
			    System.out.println("File Not Found!");
		    }  
		    pw.close();
		}
	}

	
	synchronized public void ChangeBook(OutputStream os, InputStream is) throws IOException {
		list.clear();  //����ͼ����Ϣǰ������ά���б�
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "",Name = "",Author = "",Price = "";
		ID = dis.readUTF();  //��ȡ������ͼ����Ϣ
		Name = dis.readUTF();
		Author = dis.readUTF();
		Price = dis.readUTF();
		Book book = new Book(ID,Name,Author,Double.parseDouble(Price));  //�½���ͼ��
		if(Search(book))  //�����������Ϣ����
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)  //���б��в��Ҹ�ͼ��
			{
				if(list.get(i).equals(book))
				{
					Book b = list.get(i);
					b.setAuthor(Author);  //�ҵ��������Ϣ
					b.setID(ID);
					b.setName(Name);
					b.setPrice(Double.parseDouble(Price));
					break;
				}
			}
			File f = new File(FileAddr);
		    FileWriter fw = new FileWriter(f);  //���󽫸��ĺ����Ϣд�ص��ļ�֮��
		    PrintWriter pw = new PrintWriter(fw);
			for(int i=0;i<list.size();i++)
			{
				pw.println(list.get(i));
				pw.flush();
			}
			pw.close();
		}
		else
			dos.writeInt(0);  //�����������Ϣû���ҵ����򷵻�0
		
	}

	
	synchronized public void DeleteBook(InputStream is,OutputStream os) throws IOException {
		list.clear();  //ɾ��ͼ��֮ǰ������ά��ͼ���б���Ϣ
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "";
		ID = dis.readUTF();  //�����ɾ����Ϣ
		Book book = new Book(ID,"","",0.0);
		if(Search(book))  //���������
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)  //�ҵ�����
			{
				if(list.get(i).equals(book))
				{
				    list.remove(i);  //ɾ��
				    break;
				}
			}
			File f = new File(FileAddr);  //����д���ļ���
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
			dos.writeInt(0);	 //�������� ������0
	}

	
	public void SearchBook(InputStream is,OutputStream os) throws IOException {
		list.clear(); //����ͼ��
		GetInfo();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		String ID = "";
		ID = dis.readUTF();  //��ȡ��������Ϣ
		Book book = new Book(ID,"","",0.0);
		if(Search(book))  //ͼ�����
		{
			dos.writeInt(1);
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).equals(book))
				{
					Book b = list.get(i);  //���ش�������Ϣ
					dos.writeUTF(b.getName());
					dos.writeUTF(b.getAuthor());
					dos.writeDouble(b.getPrice());   
					break;
				}
			}
		}
		else  //�������ڣ��򷵻�0
			dos.writeInt(0);
		
	}
	
	public boolean Search(Book book) throws IOException{  //�鿴�����Ƿ����
		if(list.contains(book))
			return true;
		else
			return false;
	}

}

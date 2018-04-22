package base;

import graui.ClientFrame;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class LibraryClientIdea { //������ǿͻ���Э��ģ����
	
	public Socket s;  //�ͻ���Э���а�����ԱSocket
	public Vector<Book> list = new Vector<Book>(); //�ڱ���ά��һ��ͼ����Ϣ��list
	public DataInputStream dis = null;  //Э�������
	public DataOutputStream dos = null;  //Э��д����
	
	public LibraryClientIdea(String host, int port) throws Exception {  //���캯��������Socket���Լ������ϵĶ���д����
		s = new Socket(host, port); // ���Socket���󴴽���Ϻ��ʱ���٣�
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		Init();
	}
	
	public void Init() throws IOException{ //�б��ʼ������
		list.clear();  //����б�
		InputStreamReader isr = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		int Count;
		dos.writeInt(0);  //�����������0��ָ�Ϊ��ʼ��ָ��
		Count = dis.readInt();  //�ӷ�������ȡ��Ϣ
		String str = "";
		for(int i=0;i<Count;i++)  //��ȡͼ����Ϣ
		{
			str = br.readLine();  
			StringTokenizer st = new StringTokenizer(str, " "); // �ַ����ָ�����
			String ID = st.nextToken().trim();
			String Name = st.nextToken().trim();
			String Author = st.nextToken().trim();
			double Price = Double.parseDouble(st.nextToken().trim());
			list.add(new Book(ID,Name,Author,Price));
		}		
		
	}
	
	public void AddBook(String ID,String Name,String Author,String Price) throws IOException{
		dos.writeInt(1);       //���ͼ����Ϣʵ��Э�鷽���� �����������1��ָ��
		dos.writeUTF(ID);
		dos.writeUTF(Name);
		dos.writeUTF(Author);
		dos.writeUTF(Price);
		int i = dis.readInt();  //�ӷ����������������Ϊ0������Ѵ���
		if(i == 0)
			JOptionPane.showMessageDialog(null,"Exsisted Book!");
		else  //�����Ϊ0�����ɹ�
		{
			JOptionPane.showMessageDialog(null,"Add Successfully!");
			list.add(new Book(ID,Name,Author,Double.parseDouble(Price)));
		}
		
		
	}
	
	public void ChangeBook(String ID,String Name,String Author,String Price) throws IOException{
		dos.writeInt(2);    //������Ϣʵ��Э�鷽���� �����������2��ָ��
		dos.writeUTF(ID);
		dos.writeUTF(Name);
		dos.writeUTF(Author);
		dos.writeUTF(Price);
		int i = dis.readInt();   //�ӷ����������������Ϊ0����鲻����
		if(i == 0)
			JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
		else     //��������ڲ��Ѹ��ģ��򷵻ز�Ϊ0����ʱ�����������Ѿ����ģ�ֻ��Ҫά��һ�±���ͼ����Ϣ����
		{
		    Book book = new Book(ID,"","",0.0);
		    for(i = 0;i<list.size();i++)
		    {
			    if(list.get(i).equals(book))  //����������Ϣ�ҵ�������Ϣͼ�鲢������Ϣ
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
		dos.writeInt(3);   //ɾ��ͼ����Ϣʵ��Э�鷽���� �����������3��ָ��
		dos.writeUTF(ID);
		int i = dis.readInt();
		if(i == 0)  //�ӷ����������������Ϊ0����鲻����
			JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
		else  //��������ڣ��򷵻ز�Ϊ0����ʱ���������Ѿ�ɾ����ͼ�飬ֻ��Ҫά��������Ϣ����
		{
			Book book = new Book(ID,"","",0.0);
			for(i = 0;i<list.size();i++)  //����ͼ�鲢ɾ��
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
		dos.writeInt(4);   //��ѯͼ����Ϣʵ��Э�鷽���� �����������4��ָ��
		dos.writeUTF(ID);
		int i = dis.readInt();
		if(i == 0)   //�ӷ����������������Ϊ0����鲻����
			return null;
		else{     //��������ڣ��򷵻ز�Ϊ0����ȡ�ӷ���������������Ϣ
		    String Name = dis.readUTF();
		    String Author = dis.readUTF();
		    double Price = dis.readDouble();
		    return (new Book(ID,Name,Author,Price));
		}
	}

}

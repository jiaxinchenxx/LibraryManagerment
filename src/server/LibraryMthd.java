package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface LibraryMthd   {  //�ͻ���Э�鷽���ӿ�
	
	public void Init(OutputStream os) throws IOException;  //��ʼ������
	
    public void AddBook(InputStream is,OutputStream os) throws IOException; //��ӷ���
	
	public void ChangeBook(OutputStream os,InputStream is) throws IOException;  //����ͼ����Ϣ����
	
	public void DeleteBook(InputStream is,OutputStream os) throws IOException;  //ɾ��ͼ�鷽��
	
	public void SearchBook(InputStream is,OutputStream os) throws IOException; //����ͼ�鷽��

}

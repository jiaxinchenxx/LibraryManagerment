package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface LibraryMthd   {  //客户端协议方法接口
	
	public void Init(OutputStream os) throws IOException;  //初始化方法
	
    public void AddBook(InputStream is,OutputStream os) throws IOException; //添加方法
	
	public void ChangeBook(OutputStream os,InputStream is) throws IOException;  //更改图书信息方法
	
	public void DeleteBook(InputStream is,OutputStream os) throws IOException;  //删除图书方法
	
	public void SearchBook(InputStream is,OutputStream os) throws IOException; //查找图书方法

}

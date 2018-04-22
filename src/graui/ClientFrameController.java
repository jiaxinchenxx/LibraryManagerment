package graui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.AncestorListener;

import base.*;

public class ClientFrameController implements ActionListener {

	LibraryClientIdea lci;
	ClientFrame cf;

	
	public ClientFrameController(LibraryClientIdea lci,ClientFrame cf){
		this.lci = lci;
		this.cf = cf;
		cf.addController(this);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cf.Add)  //添加按钮的响应机制
		{
			try {
				String ID = cf.AddFieldID.getText();  //获取待添加的图书信息
				String Name = cf.AddFieldName.getText();
				String Author = cf.AddFieldAuthor.getText();
				String Price = cf.AddFieldPrice.getText();
				if(Price.length()==0||ID.length()==0||Name.length()==0||Author.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The RightInfo !"); //若有一个为空则提示错误
				else  //若格式正确则添加
				{
				    lci.AddBook(ID,Name,Author,Price);
				    cf.SetModel();
				    cf.AddFieldID.setText("");  //添加完毕后清空文本框
				    cf.AddFieldName.setText("");
				    cf.AddFieldAuthor.setText("");
				    cf.AddFieldPrice.setText("");
				}
				    
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cf.Change)  //更改信息按钮的响应机制
		{
			try{
				String ID = cf.ChangeFieldID.getText();//获取待更改的图书信息
				String Name = cf.ChangeFieldName.getText();
				String Author = cf.ChangeFieldAuthor.getText();
				String Price = cf.ChangeFieldPrice.getText();
				if(Price.length()==0||ID.length()==0||Name.length()==0||Author.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//若有一个为空则提示错误
				else  //若格式正确则更改
				{
				    lci.ChangeBook(ID,Name,Author,Price);
				    cf.SetModel();
				    cf.ChangeFieldID.setText(""); //更改完毕后清空文本框
				    cf.ChangeFieldName.setText("");
				    cf.ChangeFieldAuthor.setText("");
				    cf.ChangeFieldPrice.setText("");
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cf.Delete)  //删除信息按钮的响应机制
		{
			try{
				String ID = cf.DeleteFieldID.getText();//获取待删除的图书信息
				if(ID.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//若有一个为空则提示错误
				else{   //若格式正确则删除
				    lci.DeleteBook(ID);	
				    cf.SetModel();
				    cf.DeleteFieldID.setText("");//删除完毕后清空文本框
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource() == cf.Search)//查找信息按钮的响应机制
		{
			try{
				String ID = cf.SearchFieldID.getText();//获取待查找的图书信息
				if(ID.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//若有一个为空则提示错误
				else//若格式正确则查找
				{
					
				    Book b = lci.SearchBook(ID);
				    if(b==null)    //若查找不成功则提示
				    	JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
				    else{  //若查找成功则输出到TextArea
				    String str = "ID : " + b.getID() + "\r\n" + "Name : " 
				    + b.getName() + "\r\n" + "Author : " 
				    + b.getAuthor() + "\r\n" + "Price : " 
				    + b.getPrice() + "\r\n";
				    cf.SearchResult.setText(str);
				    cf.SearchFieldID.setText("");
				    }
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else
		{
			try {  //刷新函数实现
				lci.Init();
				cf.SetModel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
			
	}

}

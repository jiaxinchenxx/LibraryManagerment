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
		if(e.getSource() == cf.Add)  //��Ӱ�ť����Ӧ����
		{
			try {
				String ID = cf.AddFieldID.getText();  //��ȡ����ӵ�ͼ����Ϣ
				String Name = cf.AddFieldName.getText();
				String Author = cf.AddFieldAuthor.getText();
				String Price = cf.AddFieldPrice.getText();
				if(Price.length()==0||ID.length()==0||Name.length()==0||Author.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The RightInfo !"); //����һ��Ϊ������ʾ����
				else  //����ʽ��ȷ�����
				{
				    lci.AddBook(ID,Name,Author,Price);
				    cf.SetModel();
				    cf.AddFieldID.setText("");  //�����Ϻ�����ı���
				    cf.AddFieldName.setText("");
				    cf.AddFieldAuthor.setText("");
				    cf.AddFieldPrice.setText("");
				}
				    
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cf.Change)  //������Ϣ��ť����Ӧ����
		{
			try{
				String ID = cf.ChangeFieldID.getText();//��ȡ�����ĵ�ͼ����Ϣ
				String Name = cf.ChangeFieldName.getText();
				String Author = cf.ChangeFieldAuthor.getText();
				String Price = cf.ChangeFieldPrice.getText();
				if(Price.length()==0||ID.length()==0||Name.length()==0||Author.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//����һ��Ϊ������ʾ����
				else  //����ʽ��ȷ�����
				{
				    lci.ChangeBook(ID,Name,Author,Price);
				    cf.SetModel();
				    cf.ChangeFieldID.setText(""); //������Ϻ�����ı���
				    cf.ChangeFieldName.setText("");
				    cf.ChangeFieldAuthor.setText("");
				    cf.ChangeFieldPrice.setText("");
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cf.Delete)  //ɾ����Ϣ��ť����Ӧ����
		{
			try{
				String ID = cf.DeleteFieldID.getText();//��ȡ��ɾ����ͼ����Ϣ
				if(ID.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//����һ��Ϊ������ʾ����
				else{   //����ʽ��ȷ��ɾ��
				    lci.DeleteBook(ID);	
				    cf.SetModel();
				    cf.DeleteFieldID.setText("");//ɾ����Ϻ�����ı���
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource() == cf.Search)//������Ϣ��ť����Ӧ����
		{
			try{
				String ID = cf.SearchFieldID.getText();//��ȡ�����ҵ�ͼ����Ϣ
				if(ID.length()==0)
					JOptionPane.showMessageDialog(null,"Please Input The Right Info !");//����һ��Ϊ������ʾ����
				else//����ʽ��ȷ�����
				{
					
				    Book b = lci.SearchBook(ID);
				    if(b==null)    //�����Ҳ��ɹ�����ʾ
				    	JOptionPane.showMessageDialog(null,"NoneExsisted Book!");
				    else{  //�����ҳɹ��������TextArea
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
			try {  //ˢ�º���ʵ��
				lci.Init();
				cf.SetModel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
			
	}

}

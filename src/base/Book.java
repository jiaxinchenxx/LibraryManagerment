package base;



import java.io.*;

public class Book implements Serializable,Cloneable {  //ͼ���࣬������ͼ��ʵ��

     private String ID,Name,Author;  //String�����ţ������Լ�����
     private double Price;
     
     public Book() {}

 	 public Book(String ID, String Name, String Author,double Price) {
 		this.ID = ID;
 		this.Name = Name;
 		this.Author = Author;
 		this.Price = Price;
 	 }

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public boolean equals(Object obj){ 
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(obj instanceof Book)
		{
			Book b = (Book)obj;
			if(this.ID.equals(b.ID))
				return true;
			else
				return false;
		}
		return false;
	}

	public String toString() {  //toString����������ʽ����ʵ��println�ĸ�ʽ������
		return ID + " " + Name + " " + Author + " " + Price;
	}
	
 	 
 	 
    
	

}

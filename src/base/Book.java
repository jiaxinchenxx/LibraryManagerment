package base;



import java.io.*;

public class Book implements Serializable,Cloneable {  //图书类，基本的图书实现

     private String ID,Name,Author;  //String类的书号，书名以及作者
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

	public String toString() {  //toString函数重载形式，以实现println的格式化传输
		return ID + " " + Name + " " + Author + " " + Price;
	}
	
 	 
 	 
    
	

}
